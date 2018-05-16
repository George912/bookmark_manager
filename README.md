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
            <li><b>Job:</b> C:\Users\YANesterov\.m2\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar</li>
            <li><b>Home:</b> C:\Users\neste_000.MYMACHINE\.m2\repository\com\h2database\h2\1.4.196\h2-1.4.196.jar</li>
        </ol>
        <h3>sql-maven-plugin versions</h3>
        Modify bookmark_manager/pom.xml. Set sql.version property:
        <ol>
            <li><b>Job:</b> 3.0.0</li>
            <li><b>Home:</b> 3.0.0-SNAPSHOT</li>
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
             <li>
                <h4>дублируется имя таблицы при выполнении операций Hibernate к БД</h4>
                Получается: при реализации иерархического соединения - получении подкатегорий категории.<br />
                Состояние модели Category - коммит bfbaa72ab58b3e1b892d165948131854ceb72b6e <br />
                Причина:
                Решение:
            </li>
            <li>
                <h4>org.hibernate.TransientObjectException: object references an unsaved transient instance</h4>
            </li>
        </ol>
    </li>
    <li>
        <h2>TODOs</h2>
        <ol>
            <li>debug configuration</li>
            <li>spring config move in container project</li>
        </ol>
    </li>
    <li>
        <h2>How to</h2>
        <ol>
            <li>
            <b>debug configuration:</b>
            https://stackoverflow.com/questions/40703688/how-to-run-tomcat7-maven-plugin-in-debug-mode-with-intellij-idea
            </li>
        </ol>
    </li>
     <li>
        <h2>tasks</h2>
        <ol>
            <li>
            </li>
            <li>
            </li>
            <li>
            </li>
        </ol>
    </li>
</ol>