

def call(String dif) {
  def white_list = """
                 genetic_assessor_old\|view_client\|web_client\|panorays_backend\|pocs\|company-pipe\|questionnaire_backoffice\|openidconnect-provider\|platform\|common\|backups\|access_lib\|whois_info\|built_with\|dns_recon\|infra_lib\|typos_phished_domains\|phones_service\|auth-management\|port_scanner\|project_generator\|project_generator_company\|project_generator_funnel\|project_generator_funnel_company\|assessments\|breached_accounts_bot\|cms_bot\|dns_security\|mail_server\|isup_bot\|pano_whois_bot\|people_gapi_bot\|hotbot_employees_scouter\|http_headers_and_methods\|sslyze_bot\|subdomains\|virustotal_old\|wappalyzer\|mail_service\|emails_harvester\|attack_surface_funnel\|company_ui_throttler\|exploit_database_feed\|htbridge_ssl_parser\|people_social_profile_harvester\|phishtank_feed\|reverse_whois\|social_info_extractor\|social_profile_fetcher\|threat_crowd\|waf_detection\|vulndb_feed\|init_db_controller\|ssl_vulnerabilities\|website_crawler\|li_recon\|monitoring_service\|botnets_funnel\|cms_funnel\|cve_search_feed\|webserver_funnel\|dns_funnel\|domain_hijacking_funnel\|domain_targeted_attacks_funnel\|domains_funnel\|mail_server_funnel\|ssl_funnel\|pano_people_funnel\|pano_people_funnel_old\|social_posture_funnel\|web_apps_funnel\|questionnaire_feed\|dashboard_serving_service\|company_service\|files_access\|virustotal_bot\|website_purpose_speculator_bot\|surface-ui\|shodan_bot\|company-api\|htbridge_subdomain_finder\|panorays_website\|research_standalone_tools\|task-api\|public_web_client\|open_bug_bounty_feed\|wiki_techs_feed\|ratings\|twitter_profile_fakeness_evaluator\|socrates\|inquiry-ui\|socrates_gcp\|socrates-front\|socrates-api\|domain_to_ips\|ips_funnel\|pano_people\|reverse_ip\|monitoring_init_dummy\|monitoring_end_dummy\|api_calls_bot\|lisa\|jenkins-test-repo2\|monitoring_api_calls\|norma\|Deployment\|news_feed\|news_funnel\|commitizen2\|git-releaser\|auto_changelog\|bumpversion\|demo_repo\|setup-rep\|the_transporter\|API Template\|Rating API\|PanoSchemas\|google_search_exploits\|notification-api\|api-toolBox\|chunky_monkey\|email_user_enumeration_bot\|active-inquiry-notification\|google_queries_exploit_db_feed\|bot_owa\|web_techs_analyzer\|logs_backup_service\|ratings_diff\|company_executive_report_creator\|Company-enricher\|dns_wildcard_bot\|cronus\|wp_vulns\|bots-deployments\|jobs\|surface-versions\|mongo_reports\|bambenek_feed\|pipes\|cyber_cure_feed\|transport\|exports-app\|last-log\|research_dashboard\|base_common\|firehol_feed\|jenkins_shared\|example_node_test\|trans_runner\|cymon_bot\|censys_bot\|linkedin_gscraper\|panocore\|build_base\|eng_common\|pano_breached\|ratings_v2\|employee_funnel\|account-api\|sanity\|workflow_status\|company_explore_tool\|localize-api\|audit-api\|stalker-api\|email_finder\|sanity_process\|dkim_finder\|example_bot_test\|techs_detective_bot\|py_standard_version\|project_generator_feed\|common_subdomain_prefixes_feed\|file-api\|cms_latest_versions_feed\|dataflow_examples\|dnssec_bot\|subdomains_bruteforcer_bot\|local-workspace\|builtwith_asset_finder\|website_link_discovery\|binary_edge_ip_risk\|binary_edge_subdomains\|hostio_bot\|suppliers_funnel\|mitigations_structure_tests\|bot_cookiecutter\|port_mapper\|company_cloner\|geolocation_bot\|ssh_vulnerabilities_bot\|ui-toolbox\|funnel_cookiecutter\|cp_ratings\|hostio_asn_bot\|cp_attack_surface_funnel\|salesforce_integration\|application_security_funnel\|risk-api\|snmp_default_community_string\|surface-deployment\|open_dns_resolvers\|ports_funnel\|techs_funnel\|papi\|ntp_commands\|error_reporting_digest\|dark_web_trends\|wiki\|comment-api\|domains_discovery_funnel\|dark_web_trends_feedback_loop\|assets_enrichment_funnel\|cypress-test\|looker_model\|subdomains_discovery_funnel\|ips_discovery_funnel\|mongo_access\|monthly_techs_report\|dynamics_ratings_bot\|oc_monitor\|connection-approval-expiration\|smerger\|cve_search_feed_py3\|feed_cookiecutter\|slabuga\|salesforce_integrator\|formulab-api\|security_statements_discovery_bot\|py_engine_api\|inquiry-api\|metadata-api\|hostio_assets_bot\|hostio_company_bot\|relationize\|change-streams\|salesforce-sync\|engine-api\|is_up_worker\|breach_articles_feed\|eng-interview\|web_metadata_worker\|worker_cookiecutter\|breach_events_feed\|mongo_utils\|research_demos\|is_up_wait_bot\|e2e-tests\|virus_total_worker\|sslyze_worker\|cp_ratings_diff\|ip_range_feed\|sanity_process_staging\|bots_phase_workers_wait_bot\|tls_funnel\|mongo-migrations\|mail_server_user_enumeration_worker\|engine_tool\|techs_company_funnel\|techs_benchmark_feed\|jira-db\|travis\|panorays-orbs\|oc_verify\|web_download_worker\|web_scrape_worker\|ubs_integration_feed\|enrichment_phase_workers_wait_bot\|cloud_discovery_funnel\|cloud_enrichment_funnel\|Bambenek_feed_py3\|transporter_scheduler\|JFrog Jira intergration\|whois_worker\|billing-api\|open_bug_bounty_worker\|codefresh\|dns_worker\|company_enricher_bot\|dns_a_worker\|port_mapper_worker\|mongodb_migration\|cdthon\|reports_diff\|ip_ranges_discovery_funnel\|botnets_discovery_funnel\|reverse_whois_py3\|company_reverse_whois\|threatcrowd_subdomains_worker\|reverse_certificates_subdomains_worker\|subdomains_discovery_phase_workers_wait_bot\|domain_discovery_flow\|zendesk-sso\|domain_similarity_process_worker\|fqdn_assets_flush_funnel\|cloud_providers_ip_ranges_feed\|snowflake_integration_feed\|timeline\|hudson_rock_feed\|salesforce_integration_feed\|service_now\|kovrr_integration_feed\|techs_detective_worker\|airflow-etl-monitor\|wp_vulns_worker\|builtwith_worker\|domain_relationships_update_worker\|techs_funnel_worker\|funnels_phase_workers_wait_bot\|hostio_domain_worker\|hostio_related_worker\|viewdns_reverse_whois_worker\|builtwith_relationships_worker\|micro-security-passport\|domain_relationship_discovery_worker\|remediation\|micro-reports\|companies_ip_ranges_feed\|endpoints_security_matching_bot\|slackBot\|fraudlogix_feed_old_repo_size_too_large\|endpoint_security_funnel\|endpoint_security_worker\|kafka-etl\|opa-policies\|DELETEABLE_SLACK_INTEGRATIONS_PLAYGROUND\|web_crawler_worker\|task\|mongo-dump-data\|fraudlogix_feed\|reports\|cyren_worker\|sixgill_integration_feed\|cyber_insights_feed\|codefresh-gitops\|codefresh-gitops_git-source\|cfgo-runtime\|papi-e2e\|hydra\|subdomain_common_prefixes_feed\|subdomains_bruteforcer_worker\|subdomains_binary_edge_worker\|subdomain_discovery_flow\|suppliers_to_companies_feed\|pg-dump-data\|infra-gitops\|the_transporter_keep_alive\|risk-insights\|micro-risk-insights\|ips_discovery_flow\|supply_chain_detection\|assets_importance_funnel\|worker_report_compare_tool\|engine_e2e\|ip_range_location_feed\|wappalyzer_worker\|python_wappalyzer_package\|tech_to_host_feed\|cyber_news_discovery_feed\|e2e-performance\|nlp_poc\|cronjob-manager\|argo-workflows\|micro-activity-center\|test-new-deploy\|nlp_service_worker\|workflow\|sorry-cypress-db
                 """
  def output=sh(script: """
          declare -a array
          prev=""
          next=""


          while IFS= read -r line
          do 
              if [[ $line =~ ^\-.* ]]; then
                  # Get the service from the line
                  key=$(echo $line | awk -F ":" '{print $1}' | sed 's/^-//' | tr '[:upper:]' '[:lower:]' | sed 's/_ver_latest//g' | sed 's/_/-/g')
                  # Get the previous tag from the line
                  prev=$(echo $line | awk -F ":" '{print $2}' | tr -d '"' | tr -d '[:space:]')
                  array+=("$key:$prev->")
              else
                  # Get the last index of the array
                  next=$(echo $line | awk -F ":" '{print $2}' | tr -d '"' | tr -d '[:space:]')
                  lastIndex=$(( ${#array[@]} - 1 ))

                  # Update the value at the last index with the "next" tag
                  array[$lastIndex]="${array[lastIndex]}$next"
              fi
          done < <(echo "$dif" | grep '^[+-]' | grep "$white_list" | grep -v 'image' | grep [0-9] | sort -k2)


          for element in "${array[@]}"; do
              service=$(echo "$element" | awk -F ":" '{print $1}' | tr -d '[:space:]')
              tags=$(echo "$element" | awk -F ":" '{print $2}')
              localprev=$(echo "$tags" | awk -F "->" '{print $1}')
              localnext=$(echo "$tags" | awk -F "->" '{print $2}')

              
              rm -rf $service.git
              git clone --bare  --no-checkout --single-branch --branch master "git@bitbucket.org:panorays/${service}.git"
              cd $service.git

              pegs_strings=$(echo $(git log --pretty=format:"%s" "v$localnext"..."v$localprev" | grep -o "PEG-[0-9]\+" |  tr ' ' '\n' | sort | uniq | awk '{print "https://panorays.atlassian.net/browse/"$0}'))
              cd ..
              echo "${service}:${localnext} ${pegs_strings}"
              rm -rf $service.git
          done
    """)
    println(output)
}
