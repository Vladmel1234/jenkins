import groovy.json.JsonSlurper
import java.util.regex.Pattern
import java.util.regex.Matcher


 
 def diff = """
 default, notification-api, Deployment (apps) has changed:
  # Source: panorays-web-service/charts/notification-api/templates/deployment.yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: notification-api
  spec:
    replicas:  1
    selector:
      matchLabels:
        app: notification-api
        tier: backend
        release: prod-panorays-web-service
    template:
      metadata:
        labels:
          app: notification-api
          tier: backend
          release: prod-panorays-web-service
        annotations:
          checksum/config: a730790231a158c75bdeee1342f4a09731ab83d3cc1b52954265b4304aedb905
          checksum/global:  4d220824af875967708c656faebfabd472980cf164a775eab4c8bca90a3eeced 
          checksum/secrets:  ead31901b329dbfd182c677aff8c80dda6e0edf59f944a86970ea52d4ea0d0d5 
          prometheus.io/scrape: 'true'
          prometheus.io/port: "9008"
      spec:
        serviceAccountName: notification-api
        affinity:
          podAntiAffinity:
            preferredDuringSchedulingIgnoredDuringExecution:
            - weight: 1
              podAffinityTerm:
                labelSelector:
                  matchExpressions:
                    - key: app
                      operator: NotIn
                      values:
                      - notification-api
                topologyKey: "kubernetes.io/hostname"
        containers:
          - name: notification-api
-           image: eu.gcr.io/panorays-main/notification-api:1.53.48
+           image: eu.gcr.io/panorays-main/notification-api:1.53.49
            imagePullPolicy: IfNotPresent
            ports:
              - containerPort: 5005
            envFrom:
              - secretRef:
                  name: secrets
              - configMapRef:
                  name: global
              - configMapRef:
                  name: notification-api
            livenessProbe:
              httpGet:
                path: /ping
                port: 5005
              initialDelaySeconds: 30
              periodSeconds: 30
            readinessProbe:
              httpGet:
                path: /ping
                port: 5005
              initialDelaySeconds: 10
              periodSeconds: 10
              timeoutSeconds: 5
            resources:
              requests:
                cpu: 200m
                memory: 200Mi
default, versions, ConfigMap (v1) has changed:
  # Source: panorays-web-service/templates/versions.yaml
  apiVersion: v1
  kind: ConfigMap
  metadata:
    name: versions
  data:
    account-api: "1.38.28"
    active-inquiry-notification-evaluators: "1.17.6"
    active-inquiry-notification-suppliers: "1.17.6"
    comment-api: "1.21.12"
    company-api: "1.54.81"
    company-enricher: "1.26.5"
    company-pipe: "1.31.0"
    connection-approval-expiration: "1.2.2"
    exports-api: "1.54.25"
    exports-app: "1.36.1"
    exports-consumer: "1.54.26"
    exports-ui: "1.54.35"
    file-api: "1.25.9"
    formulab-api: "1.13.7"
    inquiry-api: "1.33.24"
    inquiry-consumer: "1.33.23"
    inquiry-ui: "2.16.0"
    localize-api: "1.14.6"
    metadata-api: "1.26.10"
    micro-nginx: "1.5.0"
    norma: "2.3.1"
-   notification-api: "1.53.48"
+   notification-api: "1.53.49"
    openidconnect-provider: "3.0.12"
    papi: "1.27.12"
    papi-redoc: "1.3.1"
    publish-comments-from-evaluators: "1.17.6"
    publish-comments-from-mitigators: "1.17.6"
    publish-expired-inquiry: "1.17.6"
    rating-api: "2.7.11"
    reevaluation-process: "1.0.3"
    reports-api: "0.4.51"
    reports-api-schema-migration: "0.4.48"
    reports-consumer: "0.4.54"
    risk-api: "1.26.20"
    risk-insights-api: "0.0.13"
    risk-insights-api-schema-migration: "0.0.7"
    risk-insights-consumer: "0.0.11"
    stalker-api: "1.13.3"
    surface-ui: "2.1.202"
    task-api: "0.6.32"
    task-api-schema-migration: "0.6.29"
    task-consumer: "0.6.25"
    timeline-api: "1.15.11"
    timeline-api-schema-migration: "1.15.11"
    timeline-consumer: "1.15.12"
    timeline-consumer-informational: "1.15.9"
"""



def getUpdatedServicesWithPegs(dif) {
   def serviceVersionMap = [:]
    def authString = "${'VladMelnik'}:${'ATBB7gU69ZJVbxZTtZ9L2J6TPtzE0581081C'}".bytes.encodeBase64().toString()
    def humanCommitMessageArray = []

    Pattern pattern = Pattern.compile("(?m)^\\+(?!.*(image|checksum|SURFACE|PREV)).*"); 
    Matcher matcher = pattern.matcher(dif);


    while (matcher.find()) {
        println(matcher.group())
        matcher.group().split("\n").each { line ->
            line.substring(1).split(":").with { 
                serviceVersionMap[it[0].trim().toLowerCase().replace('_ver_latest', '').replace('_', '-')] = it[1].trim().replace('"', '')
            }        
        }
    }
    
    if (serviceVersionMap.size() == 0) {
        return "No commits found"
    }
    serviceVersionMap.each { k, v ->
    def urlString = "https://api.bitbucket.org/2.0/repositories/panorays/$k/commits/v$v"

    def humanCommitMessage = "$k: $v "
    def humanCommitMessageWithPeg = ""



    def url = new URL(urlString)
    def connection = url.openConnection()
    connection.setRequestProperty('Authorization', "Basic ${authString}")
        def jsonSlurper = new JsonSlurper()
        def commitData = jsonSlurper.parse(connection.getInputStream())['values'][1..-1]

        for (int i = 0; i < commitData.size(); i++) {
            def commit = commitData[i]
            if (!commit['author']['raw'].contains('Jenkins')) {
                def pegPattern = Pattern.compile("PEG-\\d+")
                def pegMatcher = pegPattern.matcher(commit['message'])
                if (pegMatcher.find()) {
                    def specificPeg = pegMatcher.group(0)
                    if(!humanCommitMessage.contains(specificPeg)) {
                        humanCommitMessage += ",${pegMatcher.group(0)}"
                    }
            
                
                }
            } else {
                break
            }
        }

        humanCommitMessageArray.add(humanCommitMessage)


    }

        if (humanCommitMessageArray.size() > 0) {
            return humanCommitMessageArray.unique().join("\n")
        }

    return "No commits found"
}

def string = getUpdatedServicesWithPegs(diff)

return this
