# Apache MiNiFi Toolkit UI Wrapper

Apache MiNiFi is the next generation of data flow management and processing. MiNiFi allows data flow administrator to reach devices further out on the edge than previously possible. Given the lightweight nature of Apache MiNiFi certain elements have been stripped away to allow for smaller binary size when running on embedded devices. A UI designer happened to be one of the things that had to go. Apache MiNiFi relies on data flow administrators to instead develop the flows they would like to run on Apache NiFi and then convert those XML templates to the MiNiFi friendly YML format. That transformation requires downloading a utility and running it from the commandline. This approach is not always possible for administrators operating in limited environments. This utility aims to help with this issue by allowing administrators/users to upload an existing NiFi XML and then outputting the MiNiFi YML without the need for invoking the commandline toolkit.

## Running

There are 3 methods provided to run this project. 

- Public Web: http://minifi.jeremydyer.me - Please note this is on a small public server and only provided for convenience purposes and cannot be guaranteed to always be running.
- Docker: ```docker run -it -d -p 9050:9050 jdye64/minifi-toolkit-ui:0.1.0-SNAPSHOT && open http://localhost:9050```
- Standard Java: ```java -jar ./releases/minifi-toolkit-ui-0.1.0-SNAPSHOT.jar server application.yml``` then open your browser to http://localhost:9050