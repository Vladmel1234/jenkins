import re

multiline_string = "default, micro-nginx, ConfigMap (v1) has changed:
  # Source: panorays-web-service/charts/micro-nginx/templates/configmap.yaml
  apiVersion: v1
  kind: ConfigMap
  metadata:
    name: micro-nginx
    labels:
      app: micro-nginx
      tier: frontend
      release: prod-panorays-web-service
  data:
    
    MICRO_ACTIVITY_CENTER_VER_LATEST: 5597c7d
    MICRO_ACTIVITY_CENTER_VER_PREV1: 5597c7d
-   MICRO_REPORTS_VER_LATEST: 1.0.13
-   MICRO_REPORTS_VER_PREV1: 1.0.12
+   MICRO_REPORTS_VER_LATEST: 1.0.14
+   MICRO_REPORTS_VER_PREV1: 1.0.13
    MICRO_RISK_INSIGHTS_VER_LATEST: 0.5.21
    MICRO_RISK_INSIGHTS_VER_PREV1: 0.5.20
    MICRO_SECURITY_PASSPORT_VER_LATEST: 2.0.10
    MICRO_SECURITY_PASSPORT_VER_PREV1: 2.0.9
    MNT_DIR: /usr/share/nginx/bucket-data
-   SURFACE_UI_VER_LATEST: 2.1.206
-   SURFACE_UI_VER_PREV1: 2.1.202
-   UI_TOOLBOX_VER_LATEST: 2.0.7
-   UI_TOOLBOX_VER_PREV1: 2.0.6
+   SURFACE_UI_VER_LATEST: 2.1.207
+   SURFACE_UI_VER_PREV1: 2.1.206
+   UI_TOOLBOX_VER_LATEST: 2.0.8
+   UI_TOOLBOX_VER_PREV1: 2.0.7
default, micro-nginx, Deployment (apps) has changed:
  # Source: panorays-web-service/charts/micro-nginx/templates/deployment.yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: micro-nginx
  spec:
    replicas:  3
    selector:
      matchLabels:
        app: micro-nginx
        tier: frontend
        release: prod-panorays-web-service
    template:
      metadata:
        labels:
          app: micro-nginx
          tier: frontend
          release: prod-panorays-web-service
        annotations:
-         checksum/config: cd20ef32d3d2a46ddd82587412633f0e94805b1dab50ae740fb1fda89f4d6df1
+         checksum/config: d996e7e4c9b80401dc96ee566a335852f8141938e0f5a59983f28cc2532065ce
          checksum/global:  4d220824af875967708c656faebfabd472980cf164a775eab4c8bca90a3eeced 
          checksum/secrets:  ead31901b329dbfd182c677aff8c80dda6e0edf59f944a86970ea52d4ea0d0d5 
      spec:
        serviceAccountName: micro-nginx
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
                      - micro-nginx
                topologyKey: "kubernetes.io/hostname"
        containers:
          - name: micro-nginx
            image: eu.gcr.io/panorays-main/micro-nginx-gcs:1.5.0
            imagePullPolicy: IfNotPresent
            ports:
              - containerPort: 80
            envFrom:
              - secretRef:
                  name: secrets
              - configMapRef:
                  name: global
              - configMapRef:
                  name: micro-nginx
            livenessProbe:
              httpGet:
                path: /index.html
                port: 80
              initialDelaySeconds: 30
              periodSeconds: 30
            readinessProbe:
              httpGet:
                path: /index.html
                port: 80
              initialDelaySeconds: 10
              periodSeconds: 10
              timeoutSeconds: 5
            resources:
              requests:
                cpu: 200m
                memory: 200Mi
            volumeMounts:
              - name: csi-gcs-pvc
                mountPath: /usr/share/nginx/bucket-data
        volumes:
          - name: csi-gcs-pvc
            persistentVolumeClaim:
              claimName: default-csi-gcs-pvc
default, surface-ui, Deployment (apps) has changed:
  # Source: panorays-web-service/charts/surface-ui/templates/deployment.yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: surface-ui
  spec:
    replicas:  5
    selector:
      matchLabels:
        app: surface-ui
        tier: frontend
        release: prod-panorays-web-service
    template:
      metadata:
        labels:
          app: surface-ui
          tier: frontend
          release: prod-panorays-web-service
        annotations:
          checksum/config: adec39d44dbb4316d289697f29da5062eb910182fb0adaa3eb5116cdc22d06c1
          checksum/global:  4d220824af875967708c656faebfabd472980cf164a775eab4c8bca90a3eeced 
          checksum/secrets:  ead31901b329dbfd182c677aff8c80dda6e0edf59f944a86970ea52d4ea0d0d5 
      spec:
        serviceAccountName: surface-ui
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
                      - surface-ui
                topologyKey: "kubernetes.io/hostname"
        containers:
          - name: surface-ui
-           image: eu.gcr.io/panorays-main/surface-ui:2.1.206
+           image: eu.gcr.io/panorays-main/surface-ui:2.1.207
            imagePullPolicy: IfNotPresent
            ports:
              - containerPort: 3000
            envFrom:
              - secretRef:
                  name: secrets
              - configMapRef:
                  name: global
              - configMapRef:
                  name: surface-ui
            livenessProbe:
              httpGet:
                path: /ping
                port: 3000
              initialDelaySeconds: 30
              periodSeconds: 30
            readinessProbe:
              httpGet:
                path: /ping
                port: 3000
              initialDelaySeconds: 10
              periodSeconds: 10
              timeoutSeconds: 5
            resources:
              requests:
                cpu: 200m
                memory: 200Mi
default, timeline-api, Deployment (apps) has changed:
  # Source: panorays-web-service/charts/timeline-api/templates/deployment.yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: timeline-api
  spec:
    replicas:  3
    selector:
      matchLabels:
        app: timeline-api
        tier: backend
        release: prod-panorays-web-service
    template:
      metadata:
        labels:
          app: timeline-api
          tier: backend
          release: prod-panorays-web-service
        annotations:
          checksum/config: 503079fa3858415088952ae16851a268bc2bf11dbb4f312ea61a87497196f8d0
          checksum/global:  4d220824af875967708c656faebfabd472980cf164a775eab4c8bca90a3eeced 
          checksum/secrets:  ead31901b329dbfd182c677aff8c80dda6e0edf59f944a86970ea52d4ea0d0d5 
          prometheus.io/scrape: 'true'
          prometheus.io/port: "9017"
      spec:
        serviceAccountName: timeline-api
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
                      - timeline-api
                topologyKey: "kubernetes.io/hostname"
        containers:
          - name: timeline-api
-           image: eu.gcr.io/panorays-main/timeline-api:1.15.11
+           image: eu.gcr.io/panorays-main/timeline-api:1.15.17
            imagePullPolicy: IfNotPresent
            ports:
              - containerPort: 6005
            envFrom:
              - secretRef:
                  name: secrets
              - configMapRef:
                  name: global
              - configMapRef:
                  name: timeline-api
            livenessProbe:
              httpGet:
                path: /ping
                port: 6005
              initialDelaySeconds: 30
              periodSeconds: 30
            readinessProbe:
              httpGet:
                path: /ping
                port: 6005
              initialDelaySeconds: 10
              periodSeconds: 10
              timeoutSeconds: 5
            resources:
              requests:
                cpu: 200m
                memory: 200Mi
default, timeline-consumer, Deployment (apps) has changed:
  # Source: panorays-web-service/charts/timeline-consumer/templates/deployment.yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: timeline-consumer
  spec:
    replicas:  3
    selector:
      matchLabels:
        app: timeline-consumer
        tier: backend
        release: prod-panorays-web-service
    template:
      metadata:
        labels:
          app: timeline-consumer
          tier: backend
          release: prod-panorays-web-service
        annotations:
          checksum/config: 06415cdbda2fb678ee7b42efa88ca987257472a11ba975db8cf2fbe2aafe708c
          checksum/global:  4d220824af875967708c656faebfabd472980cf164a775eab4c8bca90a3eeced 
          checksum/secrets:  ead31901b329dbfd182c677aff8c80dda6e0edf59f944a86970ea52d4ea0d0d5 
      spec:
        serviceAccountName: timeline-consumer
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
                      - timeline-consumer
                topologyKey: "kubernetes.io/hostname"
        containers:
          - name: timeline-consumer
-           image: eu.gcr.io/panorays-main/timeline-consumer:1.15.12
+           image: eu.gcr.io/panorays-main/timeline-consumer:1.15.15
            imagePullPolicy: IfNotPresent
            ports:
              - containerPort: 6007
            envFrom:
              - secretRef:
                  name: secrets
              - configMapRef:
                  name: global
              - configMapRef:
                  name: timeline-consumer
            livenessProbe:
              httpGet:
                path: /ping
                port: 6007
              initialDelaySeconds: 30
              periodSeconds: 30
            readinessProbe:
              httpGet:
                path: /ping
                port: 6007
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
    notification-api: "1.53.50"
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
-   surface-ui: "2.1.206"
+   surface-ui: "2.1.207"
    task-api: "0.6.32"
    task-api-schema-migration: "0.6.29"
    task-consumer: "0.6.25"
-   timeline-api: "1.15.11"
+   timeline-api: "1.15.17"
    timeline-api-schema-migration: "1.15.11"
-   timeline-consumer: "1.15.12"
+   timeline-consumer: "1.15.15"
    timeline-consumer-informational: "1.15.9"

pattern = r'^\+.*'
matches = re.findall(pattern, multiline_string, re.MULTILINE)

for match in matches:
    print(match)
