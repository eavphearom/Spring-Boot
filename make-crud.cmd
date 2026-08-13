@echo off
call mvnw.cmd exec:java "-Dexec.mainClass=com.example.tutorial.command.MakeCrudCommand" "-Dexec.args=%*"