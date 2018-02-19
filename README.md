# bookmark_manager
<ol>
    <li>
        <h2>Database credentials:</h2>
        login: YANESTEROV <br>
        password: online <br>
        <h3>Connection strings</h3>
        Modify META-INF/context.xml and pom.xml
        <ol>
            <li><b>Job:</b> C:\Users\YANesterov\.m2\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar</li>
            <li><b>Home:</b> jdbc:h2:file:C:\Users\neste_000.MYMACHINE\.m2\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar</li>
        </ol>
    </li>
    <li>
        <h2>Troubleshooting</h2>
        <ol>
            <li>
                <h4>maven: "duplicate declaration of version" while install</h4>
                delete duplicate dependencies in root pom.xml
            </li>
            <li>
                <h4>Plugin *** or one of its dependencies could not be resolved: Cannot access central (http://repo.maven.apache.org/maven2) in offline mode and the ***:jar:1.1 has not been downloaded from it before.</h4>
                <ul>
                    <li>
                        http://www.doublecloud.org/2015/06/how-to-change-maven-offline-mode/
                    </li>
                    <li>
                        отключить опцию "work offline" в настройках maven в idea
                    </li>
                </ul>
            </li>
            <li>
                <h4>все зависимости все подчеркнуты красным цветом</h4>
                http://qaru.site/questions/20701/import-maven-dependencies-in-intellij-idea
                + перезагрузить idea
            </li>
            <li>
                <h4>не отображаются атрибуты модели в jspx</h4>
                https://stackoverflow.com/questions/2168832/expression-language-in-jsp-not-working
            </li>
        </ol>
    </li>
    <li>
        <h2>TODOs</h2>
        <ol>
            <li>Настроить git атрибуты для файлов context.xml и pom.xml</li>
        </ol>
    </li>
</ol>