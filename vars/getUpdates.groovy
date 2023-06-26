def call(String dif, String white_list) {

   def pegs = sh(script: """bash -c \'
          declare -a array
          prev=""
          next=""

          while IFS= read -r line
          do 
              if [[ \$line =~ ^\\-.* ]]; then
                  # Get the service from the line
                  key=\$(echo \$line | awk -F ":" '{print \$1}' | sed 's/^-//' | tr '[:upper:]' '[:lower:]' | sed 's/_ver_latest//g' | sed 's/_/-/g')
                  # Get the previous tag from the line
                  prev=\$(echo \$line | awk -F ":" '{print \$2}' | tr -d '"' | tr -d '[:space:]')
                  array+=("\$key:\$prev->")
              else
                  # Get the last index of the array
                  next=\$(echo \$line | awk -F ":" '{print \$2}' | tr -d '"' | tr -d '[:space:]')
                  lastIndex=$(( \${#array[@]} - 1 ))

                  # Update the value at the last index with the "next" tag
                  array[\$lastIndex]="\${array[lastIndex]}\$next"
              fi
          done < <(echo "$dif" | grep '^[+-]' | grep "$white_list" | grep -v 'image' | grep [0-9] | sort -k2)

          for element in "\${array[@]}"; do
              service=\$(echo "\$element" | awk -F ":" '{print \$1}' | tr -d '[:space:]')
              tags=\$(echo "\$element" | awk -F ":" '{print \$2}')
              localprev=\$(echo "\$tags" | awk -F "->" '{print \$1}')
              localnext=\$(echo "\$tags" | awk -F "->" '{print \$2}')

              
              rm -rf \$service.git
              git clone --bare  --no-checkout --single-branch --branch master "git@bitbucket.org:panorays/\${service}.git"
              cd \$service.git

              pegs_strings=\$(echo \$(git log --pretty=format:"%s" "v\$localnext"..."v\$localprev" | grep -o "PEG-[0-9]\\+" |  tr ' ' '\\n' | sort | uniq | awk '{print "https://panorays.atlassian.net/browse/"\$0}'))
              cd ..
              echo "\${service}:\${localnext} \${pegs_strings}"
              rm -rf \$service.git
          done
    ' """, returnStdout: true).trim()
    println(pegs)
    return pegs
}
