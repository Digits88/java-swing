@echo off

if "%1"=="" goto usage 
if not "%1"=="" goto main

:usage
@echo usage: clean Folder1
goto :eof

:main
cd %1
del *.class
del *.ctxt
cd ..
pause