# bookmark_manager
<ol>
    <li>
        <h2>Application</h2>
        <ol>
            <li>
                <h3>Start into Intellij Idea</h3>
                <ul>
                    <li>build project: mvn install</li>
                    <li>start embedded tomcat and deploy application: tomcat plugin:run</li>
                    <li>go to http://localhost:8080/categories</li>
                </ul>
            </li>
            <li>
                <h3>Start executable war</h3>
                <ul>
                    <li>build project: mvn install</li>
                    <li>extract all from distribution/target/distributive.zip into any directory</li>
                    <li>open command line and go to this directory</li>
                    <li>execute java -jar bm.jar</li>
                    <li>go to http://localhost:8080/categories</li>
                </ul>
            </li>
        </ol>
        <ul>
            <li>build project: mvn install</li>
            <li>start embedded tomcat and deploy application: tomcat plugin:run</li>
            <li>go to http://localhost:8080/categories</li>
        </ul>
    </li>
    <li>
        <h2>Database</h2>
        <h3>Data base location</h3>
        Data base locate into db directory into working directory.
        <br/>
        <h3>Credentials</h3>
        login: YANESTEROV <br>
        password: online 
        <br/>
        <h3>Connection strings</h3>
        Modify META-INF/context.xml and pom.xml
        <ol>
            <li><b>Home:</b>jdbc:h2:file:.\db\h2-1.4.196.jar</li>
        </ol>
        <br/ >
        <h3>sql-maven-plugin versions</h3>
        Modify bookmark_manager/pom.xml. Set sql.version property:
        <ol>
            <li><b>Job:</b>3.0.0</li>
            <li><b>Home:</b>3.0.0-SNAPSHOT</li>
        </ol>
    </li>
    <li>
        <h2>TODOs</h2>
        <ol>
            <li>debug configuration</li>
            <li>spring config move in container project</li>
                <h5>today:</h5>
                <ul>
                    <li>implements setting model parent attributes through aop: algoritm, implmetation</li>
                    <li>test category editor(parent)</li>
                </ul>
        </ol>
    </li>
</ol>