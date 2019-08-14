# Integration

Zafira is integrated with multiple 3rd-party tools like Amazon, Slack, Gmail etc. You are able to add new integration in working Zafira without restart of web application. Navigate to **Integrations** icon in sidebar menu:

<p align="center">
  <img src="../img/sidebar_integrations.png">
</p>

On the integrations page you will find multiple blocks responsible for different integration modules. Pay attention to the fact that security model HTTP/HTTPS may affect your integration URL.

<p align="center">
  <img src="../img/feature_integrations.png">
</p>


#### Amazon
Zafira uses integration with Amazon S3 service to store user profile photos and company logo. First of all we will need to create new S3 bucket and generate access token to provide API access to Amazon S3 from Zafira.

* Create new S3 bucket in region you prefer
* Navigate to bucket permissions, CORS configuration and set following policy:
```
<?xml version="1.0" encoding="UTF-8"?>
<CORSConfiguration xmlns="http://s3.amazonaws.com/doc/2006-03-01/">
<CORSRule>
    <AllowedOrigin>*</AllowedOrigin>
    <AllowedMethod>GET</AllowedMethod>
    <AllowedMethod>POST</AllowedMethod>
    <MaxAgeSeconds>3000</MaxAgeSeconds>
    <AllowedHeader>*</AllowedHeader>
</CORSRule>
</CORSConfiguration>
```
* Create new IAM user with **Programmatic access**
* Grant read/write permissions for new user (read [detailed guide](https://aws.amazon.com/blogs/security/writing-iam-policies-how-to-grant-access-to-an-amazon-s3-bucket/))
* Generate new access/secret keys
* Turn on Amazon integration and provide access/secret keys and name of the bucket you've created
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_amazon.png">
</p>


#### Email
Zafira provides functionality for sending test results, widget and dashboards via email. You have to specify correct SMTP credentials to enable this feature. We are mostly using Gmail for that purposes. You can use configuration below replacing email and password with your valid Gmail credentials.

User invitations, forgot password functionality require SMTP integration to be enabled as well.

* Turn on Email integration
* Specify correct SMTP host and port
* Specify valid Gmail credentials 
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;"  width="280px" height="420px" src="../img/int_gmail.png">
</p>


#### Jenkins
Jenkins integration is used for triggering new builds and collecting test jobs configuration during the startup. Also Zafira provides remote debug and launcher functionality with Jenkins integration enabled. For Jenkins integration follow the next steps:

* Create user with READ,RUN access for jobs
* Generate access token (read [instruction](https://support.cloudbees.com/hc/en-us/articles/115003090592-How-to-re-generate-my-Jenkins-user-token))
* Paste Jenkins URL, username and token to Zafira
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;"  width="280px" height="420px" src="../img/int_jenkins.png">
</p>


#### Jira
Jira integration allows to track known issues status for failed test cases. When you assign known issue to contstantly failing test cases you may specify appropriate Jira ticket. If Jira integration is enabled, Zafira will check current ticket status and track failure as known issue if ticket opened and as unknown if it is closed, so you will never lose regression bugs.

* Paste Jira URL, username and password to Zafira
* List set of statuses that indicates that ticket is closed
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_jira.png">
</p>


#### LDAP
Zafira supports LDAP authentication, in this case on first success login via LDAP Zafira will register user details in own database. Admin will be able to manage user permissions for every new user came via LDAP. 

* Use configuration below as a reference for your LDAP connection setup
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_ldap.png">
</p>


#### SLACK
Zafira is capable to post automation results into specific Slack channels for better visibility.
If integration is set up correctly after test run is finished notification with run details will be sent into appropriate channel. Such  Slack notification contains base information on test run and also includes links to this run in Zafira and Jenkins.
After user marks some run as reviewed and Slack integration is configured for executed Jenkins job user will be proposed to send to Slack notification about reviewed run.
In order to setup Slack integration follow the next steps:

* Generate Slack web hook url and add it as parameter SLACK_WEB_HOOK_URL into SLACK block at Zafira integrations page
* For each Jenkins job you need integration for add parameters in Zafira using next pattern: **SLACK_NOTIF_CHANNEL_real_channel_name=JENKINS_JOB_1;JENKINS_JOB_2** where
  * real_channel_name - name of Slack channel to post notifications to
  * JENKINS_JOB_1 and JENKINS_JOB_2 - names of Jenkins jobs
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_slack.png">
</p>


#### GOOGLE
Google integration provides possibility to export test run results to spreadsheets.
It also makes possible to use spreadsheets data on the client side using temporary credentials provided by Zafira API (GET: /api/settings/google/creds).

In order to setup Google integration follow the next steps:

* Generate Google service account key (as file) and attach it by clicking UPLOAD CLIENT SECRET ORIGIN button in GOOGLE block at Zafira integrations page
* Press *upload*, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_google.png">
</p>


#### RABBITMQ
RabbitMQ integration is used to provide Zafira the possibility to display test logs. The integration is a gateway for the transfer of logs into Zafira.
In order to setup RabbitMQ integration follow the next steps:

* Turn on RabbitMQ integration
* Specify correct host and port
* Specify valid credentials 
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_rabbitmq.png">
</p>


#### SELENIUM
Zafira uses Selenium integration in launcher functionality scope.
It provides *Selenium server URL* used on Jenkins for determination of preferred for tests launch Selenium instance.
In order to setup Selenium integration follow the next steps:

* Turn on Selenium integration
* Specify correct URL
* Specify valid credentials if need
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_selenium.png">
</p>


#### TESTRAIL
TestRail integration is used to build links leading to TestRail test cases from Zafira test tags.
Provided tag key must use TESTRAIL_TESTCASE_UUID as key and internal Testrail test case identifier as value (Ex. 34-66-45465, where 34 - TestRail project id, 66 - TestRail test suite id, 45465 - TestRail test case id). 
In order to setup TestRail integration follow the next steps:

* Add a Testrail service URL as parameter URL into into TESTRAIL block at Zafira integrations page
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_testrail.png">
</p>


#### QTEST
Zafira supports qTest integration to build links leading to qTest test cases from Zafira test tags.
Provided tag key require to be QTEST_TESTCASE_UUID, value is an internal qTest test case identifier as value (Ex. 454-2423, where 454 - qTest project id, 2423 - qTest test case id). 
In order to setup qTest integration follow the next steps:

* Add a qTest service URL as parameter URL into into QTEST block at Zafira integrations page
* Press save, green light indicates correct integration status

<p>
  <img style="border: 1px solid grey;" width="280px" height="420px" src="../img/int_qtest.png">
</p>
