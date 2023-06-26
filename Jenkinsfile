
node {
    stage('Get services') {
 dif = """
default, active-inquiry-notification-suppliers, CronJob (batch) has changed:
  # Source: panorays-web-service/charts/active-inquiry-notification-suppliers/templates/cronjob.yaml
  apiVersion: batch/v1
  kind: CronJob
  metadata:
    name: active-inquiry-notification-suppliers
    labels:
      app: active-inquiry-notification-suppliers
      tier: cronjob
      release: prod-panorays-web-service
  spec:
    schedule: "00 13 * * *"
    jobTemplate:
      metadata:
        labels:
          app: active-inquiry-notification-suppliers
          tier: cronjob
          release: prod-panorays-web-service
      spec:
        template:
          metadata:
            labels:
              app: active-inquiry-notification-suppliers
              tier: cronjob
              release: prod-panorays-web-service
          spec:
+           serviceAccountName: active-inquiry-notification-suppliers
            containers:
              - name: active-inquiry-notification-suppliers
                image: eu.gcr.io/panorays-main/active-inquiry-notification:1.17.6
-               imagePullPolicy: Always
+               imagePullPolicy: IfNotPresent
                envFrom:
                  - secretRef:
                      name: secrets
                  - configMapRef:
                      name: global
                  - configMapRef:
                      name: active-inquiry-notification-suppliers
            restartPolicy: Never
default, connection-approval-expiration, CronJob (batch) has changed:
  # Source: panorays-web-service/charts/connection-approval-expiration/templates/cronjob.yaml
  apiVersion: batch/v1
  kind: CronJob
  metadata:
    name: connection-approval-expiration
    labels:
      app: connection-approval-expiration
      tier: cronjob
      release: prod-panorays-web-service
  spec:
    schedule: "0 16 * * *"
    jobTemplate:
      metadata:
        labels:
          app: connection-approval-expiration
          tier: cronjob
          release: prod-panorays-web-service
      spec:
        template:
          metadata:
            labels:
              app: connection-approval-expiration
              tier: cronjob
              release: prod-panorays-web-service
          spec:
+           serviceAccountName: connection-approval-expiration
            containers:
              - name: connection-approval-expiration
                image: eu.gcr.io/panorays-main/connection-approval-expiration:1.2.2
-               imagePullPolicy: Always
+               imagePullPolicy: IfNotPresent
                envFrom:
                  - secretRef:
                      name: secrets
                  - configMapRef:
                      name: global
                  - configMapRef:
                      name: connection-approval-expiration
            restartPolicy: Never
default, inquiry-api, Deployment (apps) has changed:
  # Source: panorays-web-service/charts/inquiry-api/templates/deployment.yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: inquiry-api
  spec:
    replicas:  3
    selector:
      matchLabels:
        app: inquiry-api
        tier: backend
        release: prod-panorays-web-service
    template:
      metadata:
        labels:
          app: inquiry-api
          tier: backend
          release: prod-panorays-web-service
        annotations:
          checksum/config: 9d319b2e2d7f11178cf89676992eada98f2a3d8883bf52cea0fbf88a24607834
          checksum/global:  4d220824af875967708c656faebfabd472980cf164a775eab4c8bca90a3eeced 
          checksum/secrets:  ead31901b329dbfd182c677aff8c80dda6e0edf59f944a86970ea52d4ea0d0d5 
          prometheus.io/scrape: 'true'
          prometheus.io/port: "9010"
      spec:
        serviceAccountName: inquiry-api
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
                      - inquiry-api
                topologyKey: "kubernetes.io/hostname"
        containers:
          - name: inquiry-api
-           image: eu.gcr.io/panorays-main/inquiry-api:1.33.17
+           image: eu.gcr.io/panorays-main/inquiry-api:1.33.18
            imagePullPolicy: IfNotPresent
            ports:
              - containerPort: 1234
            envFrom:
              - secretRef:
                  name: secrets
              - configMapRef:
                  name: global
              - configMapRef:
                  name: inquiry-api
            livenessProbe:
              httpGet:
                path: /ping
                port: 1234
              initialDelaySeconds: 30
              periodSeconds: 30
            readinessProbe:
              httpGet:
                path: /ping
                port: 1234
              initialDelaySeconds: 10
              periodSeconds: 10
              timeoutSeconds: 5
            resources:
              requests:
                cpu: 200m
                memory: 200Mi
default, inquiry-consumer, ConfigMap (v1) has changed:
  # Source: panorays-web-service/charts/inquiry-consumer/templates/configmap.yaml
  apiVersion: v1
  kind: ConfigMap
  metadata:
    name: inquiry-consumer
    labels:
      app: inquiry-consumer
      tier: backend
      release: prod-panorays-web-service
  data:
+   CLIENT_SECRET: "sm://projects/294101458348/secrets/prod-inquiry-consumer-CLIENT_SECRET/versions/latest"
+   CLIENT_ID: "sm://projects/294101458348/secrets/prod-inquiry-consumer-CLIENT_ID/versions/latest"
default, inquiry-consumer, Deployment (apps) has changed:
  # Source: panorays-web-service/charts/inquiry-consumer/templates/deployment.yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: inquiry-consumer
  spec:
    replicas:  3
    selector:
      matchLabels:
        app: inquiry-consumer
        tier: backend
        release: prod-panorays-web-service
    template:
      metadata:
        labels:
          app: inquiry-consumer
          tier: backend
          release: prod-panorays-web-service
        annotations:
-         checksum/config: b424b2eca5886ef6171433d80a7b40fdffba2889498bf8d2a7351519e4b3f4fd
+         checksum/config: e691c930fc8eba8a38b7cfd7e8ca8665861ae0f94af5fa22c8cc3910dd527f49
          checksum/global:  4d220824af875967708c656faebfabd472980cf164a775eab4c8bca90a3eeced 
          checksum/secrets:  ead31901b329dbfd182c677aff8c80dda6e0edf59f944a86970ea52d4ea0d0d5 
      spec:
        serviceAccountName: inquiry-consumer
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
                      - inquiry-consumer
                topologyKey: "kubernetes.io/hostname"
        containers:
          - name: inquiry-consumer
-           image: eu.gcr.io/panorays-main/inquiry-consumer:1.33.13
+           image: eu.gcr.io/panorays-main/inquiry-consumer:1.33.21
            imagePullPolicy: IfNotPresent
            ports:
              - containerPort: 4321
            envFrom:
              - secretRef:
                  name: secrets
              - configMapRef:
                  name: global
              - configMapRef:
                  name: inquiry-consumer
            livenessProbe:
              httpGet:
                path: /ping
                port: 4321
              initialDelaySeconds: 30
              periodSeconds: 30
            readinessProbe:
              httpGet:
                path: /ping
                port: 4321
              initialDelaySeconds: 10
              periodSeconds: 10
              timeoutSeconds: 5
            resources:
              requests:
                cpu: 200m
                memory: 200Mi
default, micro-nginx, ConfigMap (v1) has changed:
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
    MICRO_REPORTS_VER_LATEST: 1.0.13
    MICRO_REPORTS_VER_PREV1: 1.0.12
    MICRO_RISK_INSIGHTS_VER_LATEST: 0.5.21
    MICRO_RISK_INSIGHTS_VER_PREV1: 0.5.20
    MICRO_SECURITY_PASSPORT_VER_LATEST: 2.0.10
    MICRO_SECURITY_PASSPORT_VER_PREV1: 2.0.9
    MNT_DIR: /usr/share/nginx/bucket-data
-   SURFACE_UI_VER_LATEST: 2.1.195
-   SURFACE_UI_VER_PREV1: 2.1.194
+   SURFACE_UI_VER_LATEST: 2.1.196
+   SURFACE_UI_VER_PREV1: 2.1.195
    UI_TOOLBOX_VER_LATEST: 2.0.7
    UI_TOOLBOX_VER_PREV1: 2.0.6
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
-         checksum/config: 9f7623d034d3e6dc665ca7c37a4334f04447279694241cedb70aff9abecc6b87
+         checksum/config: 30585e626aa2477463bae9501c51d9238fd920766fb8fa7eb5b29b8fc29565d5
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
default, norma, CronJob (batch) has changed:
  # Source: panorays-web-service/charts/norma/templates/cronjob.yaml
  apiVersion: batch/v1
  kind: CronJob
  metadata:
    name: norma
    labels:
      app: norma
      tier: cronjob
      release: prod-panorays-web-service
  spec:
    schedule: "0 * * * *"
    jobTemplate:
      metadata:
        labels:
          app: norma
          tier: cronjob
          release: prod-panorays-web-service
      spec:
        template:
          metadata:
            labels:
              app: norma
              tier: cronjob
              release: prod-panorays-web-service
          spec:
+           serviceAccountName: norma
            containers:
              - name: norma
                image: eu.gcr.io/panorays-main/norma:2.3.1
-               imagePullPolicy: Always
+               imagePullPolicy: IfNotPresent
                envFrom:
                  - secretRef:
                      name: secrets
                  - configMapRef:
                      name: global
                  - configMapRef:
                      name: norma
            restartPolicy: Never
default, publish-comments-from-evaluators, CronJob (batch) has changed:
  # Source: panorays-web-service/charts/publish-comments-from-evaluators/templates/cronjob.yaml
  apiVersion: batch/v1
  kind: CronJob
  metadata:
    name: publish-comments-from-evaluators
    labels:
      app: publish-comments-from-evaluators
      tier: cronjob
      release: prod-panorays-web-service
  spec:
    schedule: "0 12 * * *"
    jobTemplate:
      metadata:
        labels:
          app: publish-comments-from-evaluators
          tier: cronjob
          release: prod-panorays-web-service
      spec:
        template:
          metadata:
            labels:
              app: publish-comments-from-evaluators
              tier: cronjob
              release: prod-panorays-web-service
          spec:
+           serviceAccountName: publish-comments-from-evaluators
            containers:
              - name: publish-comments-from-evaluators
                image: eu.gcr.io/panorays-main/active-inquiry-notification:1.17.6
-               imagePullPolicy: Always
+               imagePullPolicy: IfNotPresent
                envFrom:
                  - secretRef:
                      name: secrets
                  - configMapRef:
                      name: global
                  - configMapRef:
                      name: publish-comments-from-evaluators
            restartPolicy: Never
default, publish-comments-from-mitigators, CronJob (batch) has changed:
  # Source: panorays-web-service/charts/publish-comments-from-mitigators/templates/cronjob.yaml
  apiVersion: batch/v1
  kind: CronJob
  metadata:
    name: publish-comments-from-mitigators
    labels:
      app: publish-comments-from-mitigators
      tier: cronjob
      release: prod-panorays-web-service
  spec:
    schedule: "0 13 * * *"
    jobTemplate:
      metadata:
        labels:
          app: publish-comments-from-mitigators
          tier: cronjob
          release: prod-panorays-web-service
      spec:
        template:
          metadata:
            labels:
              app: publish-comments-from-mitigators
              tier: cronjob
              release: prod-panorays-web-service
          spec:
+           serviceAccountName: publish-comments-from-mitigators
            containers:
              - name: publish-comments-from-mitigators
                image: eu.gcr.io/panorays-main/active-inquiry-notification:1.17.6
-               imagePullPolicy: Always
+               imagePullPolicy: IfNotPresent
                envFrom:
                  - secretRef:
                      name: secrets
                  - configMapRef:
                      name: global
                  - configMapRef:
                      name: publish-comments-from-mitigators
            restartPolicy: Never
default, reevaluation-process, CronJob (batch) has changed:
  # Source: panorays-web-service/charts/reevaluation-process/templates/cronjob.yaml
  apiVersion: batch/v1
  kind: CronJob
  metadata:
    name: reevaluation-process
    labels:
      app: reevaluation-process
      tier: cronjob
      release: prod-panorays-web-service
  spec:
    schedule: "00 13 * * *"
    jobTemplate:
      metadata:
        labels:
          app: reevaluation-process
          tier: cronjob
          release: prod-panorays-web-service
      spec:
        template:
          metadata:
            labels:
              app: reevaluation-process
              tier: cronjob
              release: prod-panorays-web-service
          spec:
+           serviceAccountName: reevaluation-process
            containers:
              - name: reevaluation-process
                image: eu.gcr.io/panorays-main/cronjob-manager:1.0.3
-               imagePullPolicy: Always
+               imagePullPolicy: IfNotPresent
                envFrom:
                  - secretRef:
                      name: secrets
                  - configMapRef:
                      name: global
                  - configMapRef:
                      name: reevaluation-process
            restartPolicy: Never
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
-           image: eu.gcr.io/panorays-main/surface-ui:2.1.195
+           image: eu.gcr.io/panorays-main/surface-ui:2.1.196
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
    company-api: "1.54.80"
    company-enricher: "1.26.5"
    company-pipe: "1.31.0"
    connection-approval-expiration: "1.2.2"
    exports-api: "1.54.25"
    exports-app: "1.36.1"
    exports-consumer: "1.54.26"
    exports-ui: "1.54.35"
    file-api: "1.25.9"
    formulab-api: "1.13.7"
-   inquiry-api: "1.33.17"
-   inquiry-consumer: "1.33.13"
+   inquiry-api: "1.33.18"
+   inquiry-consumer: "1.33.21"
    inquiry-ui: "2.16.0"
    localize-api: "1.14.6"
    metadata-api: "1.26.9"
    micro-nginx: "1.5.0"
    norma: "2.3.1"
    notification-api: "1.53.45"
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
-   surface-ui: "2.1.195"
+   surface-ui: "2.1.196"
    task-api: "0.6.31"
    task-api-schema-migration: "0.6.29"
    task-consumer: "0.6.25"
    timeline-api: "1.15.11"
    timeline-api-schema-migration: "1.15.11"
    timeline-consumer: "1.15.12"
    timeline-consumer-informational: "1.15.9"
default, active-inquiry-notification-suppliers, ServiceAccount (v1) has been added:
- 
+ # Source: panorays-web-service/charts/active-inquiry-notification-suppliers/templates/serviceaccount.yaml
+ apiVersion: v1
+ kind: ServiceAccount
+ metadata:
+   name: active-inquiry-notification-suppliers
+   annotations:
+     iam.gke.io/gcp-service-account: pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-10"
default, connection-approval-expiration, ServiceAccount (v1) has been added:
- 
+ # Source: panorays-web-service/charts/connection-approval-expiration/templates/serviceaccount.yaml
+ apiVersion: v1
+ kind: ServiceAccount
+ metadata:
+   name: connection-approval-expiration
+   annotations:
+     iam.gke.io/gcp-service-account: pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-10"
default, iampolicy-secret-access-prod-inquiry-consumer-client-id, IAMPartialPolicy (iam.cnrm.cloud.google.com) has been added:
- 
+ # Source: panorays-web-service/charts/inquiry-consumer/templates/gcp-iam-policy.yaml
+ apiVersion: iam.cnrm.cloud.google.com/v1beta1
+ kind: IAMPartialPolicy
+ metadata:
+   annotations:
+     cnrm.cloud.google.com/project-id: panorays-main
+   name: iampolicy-secret-access-prod-inquiry-consumer-client-id
+ spec:
+   bindings:
+   - role: roles/secretmanager.secretAccessor
+     members:
+       - member: serviceAccount:inquiry-consumer@panorays-main.iam.gserviceaccount.com
+   resourceRef:
+     apiVersion: secretmanager.cnrm.cloud.google.com/v1beta1
+     kind: SecretManagerSecret
+     external: projects/panorays-main/secrets/prod-inquiry-consumer-CLIENT_ID
default, iampolicy-secret-access-prod-inquiry-consumer-client-secret, IAMPartialPolicy (iam.cnrm.cloud.google.com) has been added:
- 
+ # Source: panorays-web-service/charts/inquiry-consumer/templates/gcp-iam-policy.yaml
+ apiVersion: iam.cnrm.cloud.google.com/v1beta1
+ kind: IAMPartialPolicy
+ metadata:
+   annotations:
+     cnrm.cloud.google.com/project-id: panorays-main
+   name: iampolicy-secret-access-prod-inquiry-consumer-client-secret
+ spec:
+   bindings:
+   - role: roles/secretmanager.secretAccessor
+     members:
+       - member: serviceAccount:inquiry-consumer@panorays-main.iam.gserviceaccount.com
+   resourceRef:
+     apiVersion: secretmanager.cnrm.cloud.google.com/v1beta1
+     kind: SecretManagerSecret
+     external: projects/panorays-main/secrets/prod-inquiry-consumer-CLIENT_SECRET
default, iampolicy-workload-identity-active-inquiry-notification-suppliers, IAMPartialPolicy (iam.cnrm.cloud.google.com) has been added:
- 
+ # Source: panorays-web-service/charts/active-inquiry-notification-suppliers/templates/gcp-iam-policy.yaml
+ apiVersion: iam.cnrm.cloud.google.com/v1beta1
+ kind: IAMPartialPolicy
+ metadata:
+   name: iampolicy-workload-identity-active-inquiry-notification-suppliers
+   annotations:
+     meta.helm.sh/release-name: prod-panorays-web-service
+     meta.helm.sh/release-namespace: default
+     cnrm.cloud.google.com/project-id: panorays-main
+     cnrm.cloud.google.com/deletion-policy: "abandon"
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-15"
+     "helm.sh/hook-delete-policy": before-hook-creation,hook-succeeded,hook-failed
+   labels:
+     app.kubernetes.io/managed-by: Helm
+ spec:
+   resourceRef:
+     apiVersion: iam.cnrm.cloud.google.com/v1beta1
+     kind: IAMServiceAccount
+     external: projects/panorays-main/serviceAccounts/pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+   bindings:
+     - role: roles/iam.workloadIdentityUser
+       members:
+         - member: serviceAccount:panorays-main.svc.id.goog[default/active-inquiry-notification-suppliers]
default, iampolicy-workload-identity-connection-approval-expiration, IAMPartialPolicy (iam.cnrm.cloud.google.com) has been added:
- 
+ # Source: panorays-web-service/charts/connection-approval-expiration/templates/gcp-iam-policy.yaml
+ apiVersion: iam.cnrm.cloud.google.com/v1beta1
+ kind: IAMPartialPolicy
+ metadata:
+   name: iampolicy-workload-identity-connection-approval-expiration
+   annotations:
+     meta.helm.sh/release-name: prod-panorays-web-service
+     meta.helm.sh/release-namespace: default
+     cnrm.cloud.google.com/project-id: panorays-main
+     cnrm.cloud.google.com/deletion-policy: "abandon"
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-15"
+     "helm.sh/hook-delete-policy": before-hook-creation,hook-succeeded,hook-failed
+   labels:
+     app.kubernetes.io/managed-by: Helm
+ spec:
+   resourceRef:
+     apiVersion: iam.cnrm.cloud.google.com/v1beta1
+     kind: IAMServiceAccount
+     external: projects/panorays-main/serviceAccounts/pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+   bindings:
+     - role: roles/iam.workloadIdentityUser
+       members:
+         - member: serviceAccount:panorays-main.svc.id.goog[default/connection-approval-expiration]
default, iampolicy-workload-identity-norma, IAMPartialPolicy (iam.cnrm.cloud.google.com) has been added:
- 
+ # Source: panorays-web-service/charts/norma/templates/gcp-iam-policy.yaml
+ apiVersion: iam.cnrm.cloud.google.com/v1beta1
+ kind: IAMPartialPolicy
+ metadata:
+   name: iampolicy-workload-identity-norma
+   annotations:
+     meta.helm.sh/release-name: prod-panorays-web-service
+     meta.helm.sh/release-namespace: default
+     cnrm.cloud.google.com/project-id: panorays-main
+     cnrm.cloud.google.com/deletion-policy: "abandon"
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-15"
+     "helm.sh/hook-delete-policy": before-hook-creation,hook-succeeded,hook-failed
+   labels:
+     app.kubernetes.io/managed-by: Helm
+ spec:
+   resourceRef:
+     apiVersion: iam.cnrm.cloud.google.com/v1beta1
+     kind: IAMServiceAccount
+     external: projects/panorays-main/serviceAccounts/pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+   bindings:
+     - role: roles/iam.workloadIdentityUser
+       members:
+         - member: serviceAccount:panorays-main.svc.id.goog[default/norma]
default, iampolicy-workload-identity-publish-comments-from-evaluators, IAMPartialPolicy (iam.cnrm.cloud.google.com) has been added:
- 
+ # Source: panorays-web-service/charts/publish-comments-from-evaluators/templates/gcp-iam-policy.yaml
+ apiVersion: iam.cnrm.cloud.google.com/v1beta1
+ kind: IAMPartialPolicy
+ metadata:
+   name: iampolicy-workload-identity-publish-comments-from-evaluators
+   annotations:
+     meta.helm.sh/release-name: prod-panorays-web-service
+     meta.helm.sh/release-namespace: default
+     cnrm.cloud.google.com/project-id: panorays-main
+     cnrm.cloud.google.com/deletion-policy: "abandon"
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-15"
+     "helm.sh/hook-delete-policy": before-hook-creation,hook-succeeded,hook-failed
+   labels:
+     app.kubernetes.io/managed-by: Helm
+ spec:
+   resourceRef:
+     apiVersion: iam.cnrm.cloud.google.com/v1beta1
+     kind: IAMServiceAccount
+     external: projects/panorays-main/serviceAccounts/pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+   bindings:
+     - role: roles/iam.workloadIdentityUser
+       members:
+         - member: serviceAccount:panorays-main.svc.id.goog[default/publish-comments-from-evaluators]
default, iampolicy-workload-identity-publish-comments-from-mitigators, IAMPartialPolicy (iam.cnrm.cloud.google.com) has been added:
- 
+ # Source: panorays-web-service/charts/publish-comments-from-mitigators/templates/gcp-iam-policy.yaml
+ apiVersion: iam.cnrm.cloud.google.com/v1beta1
+ kind: IAMPartialPolicy
+ metadata:
+   name: iampolicy-workload-identity-publish-comments-from-mitigators
+   annotations:
+     meta.helm.sh/release-name: prod-panorays-web-service
+     meta.helm.sh/release-namespace: default
+     cnrm.cloud.google.com/project-id: panorays-main
+     cnrm.cloud.google.com/deletion-policy: "abandon"
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-15"
+     "helm.sh/hook-delete-policy": before-hook-creation,hook-succeeded,hook-failed
+   labels:
+     app.kubernetes.io/managed-by: Helm
+ spec:
+   resourceRef:
+     apiVersion: iam.cnrm.cloud.google.com/v1beta1
+     kind: IAMServiceAccount
+     external: projects/panorays-main/serviceAccounts/pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+   bindings:
+     - role: roles/iam.workloadIdentityUser
+       members:
+         - member: serviceAccount:panorays-main.svc.id.goog[default/publish-comments-from-mitigators]
default, iampolicy-workload-identity-reevaluation-process, IAMPartialPolicy (iam.cnrm.cloud.google.com) has been added:
- 
+ # Source: panorays-web-service/charts/reevaluation-process/templates/gcp-iam-policy.yaml
+ apiVersion: iam.cnrm.cloud.google.com/v1beta1
+ kind: IAMPartialPolicy
+ metadata:
+   name: iampolicy-workload-identity-reevaluation-process
+   annotations:
+     meta.helm.sh/release-name: prod-panorays-web-service
+     meta.helm.sh/release-namespace: default
+     cnrm.cloud.google.com/project-id: panorays-main
+     cnrm.cloud.google.com/deletion-policy: "abandon"
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-15"
+     "helm.sh/hook-delete-policy": before-hook-creation,hook-succeeded,hook-failed
+   labels:
+     app.kubernetes.io/managed-by: Helm
+ spec:
+   resourceRef:
+     apiVersion: iam.cnrm.cloud.google.com/v1beta1
+     kind: IAMServiceAccount
+     external: projects/panorays-main/serviceAccounts/pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+   bindings:
+     - role: roles/iam.workloadIdentityUser
+       members:
+         - member: serviceAccount:panorays-main.svc.id.goog[default/reevaluation-process]
default, norma, ServiceAccount (v1) has been added:
- 
+ # Source: panorays-web-service/charts/norma/templates/serviceaccount.yaml
+ apiVersion: v1
+ kind: ServiceAccount
+ metadata:
+   name: norma
+   annotations:
+     iam.gke.io/gcp-service-account: pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-10"
default, publish-comments-from-evaluators, ServiceAccount (v1) has been added:
- 
+ # Source: panorays-web-service/charts/publish-comments-from-evaluators/templates/serviceaccount.yaml
+ apiVersion: v1
+ kind: ServiceAccount
+ metadata:
+   name: publish-comments-from-evaluators
+   annotations:
+     iam.gke.io/gcp-service-account: pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-10"
default, publish-comments-from-mitigators, ServiceAccount (v1) has been added:
- 
+ # Source: panorays-web-service/charts/publish-comments-from-mitigators/templates/serviceaccount.yaml
+ apiVersion: v1
+ kind: ServiceAccount
+ metadata:
+   name: publish-comments-from-mitigators
+   annotations:
+     iam.gke.io/gcp-service-account: pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-10"
default, reevaluation-process, ServiceAccount (v1) has been added:
- 
+ # Source: panorays-web-service/charts/reevaluation-process/templates/serviceaccount.yaml
+ apiVersion: v1
+ kind: ServiceAccount
+ metadata:
+   name: reevaluation-process
+   annotations:
+     iam.gke.io/gcp-service-account: pws-common-cronjob@panorays-main.iam.gserviceaccount.com
+     helm.sh/hook: pre-install,pre-upgrade
+     helm.sh/hook-weight: "-10"
"""

      try {
        // writeFile file: 'dif.txt', text: dif
        // MSG=sh(script: "./get_updates.sh '${params.REPOS_WHITELIST}' dif.txt", returnStdout: true).trim()
        // sh(script: "rm dif.txt")
     getUpdates(dif)
      } catch (err) {
          // slackSend (color: 'bad', message: err, channel: 'C05D3AJB371')
      }
    }

    stage('notify with slack') {
        // slackSend (color: 'good', message: MSG, channel: 'C05D3AJB371')
    }
}
