# bookmark_manager
<ol>
    <li>
        <h2>Application start</h2>
        <ul>
            <li>build project: mvn install</li>
            <li>start embedded tomcat and deploy application: tomcat plugin:run</li>
            <li>go to http://localhost:8080/categories</li>
        </ul>
    </li>
    <li>
        <h2>Database</h2>
        <h3>Credentials</h3>
        login: YANESTEROV <br>
        password: online <br>
        <h3>Connection strings</h3>
        Modify META-INF/context.xml and pom.xml
        <ol>
            <li><b>Job:</b>C:\Users\YANesterov\.m2\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar</li>
            <li><b>Home:</b>C:\Users\neste_000.MYMACHINE\.m2\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar</li>
        </ol>
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