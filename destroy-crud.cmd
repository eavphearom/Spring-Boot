@echo off
call .\mvnw.cmd exec:java "-Dexec.mainClass=com.example.tutorial.command.DestroyCrudCommand" "-Dexec.args=%*"