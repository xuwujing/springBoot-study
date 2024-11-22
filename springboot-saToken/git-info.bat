@echo off
set time=%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%
set app=springboot-demo
for /F %%i in ('git rev-parse --short HEAD') do ( set commit_id=%%i)
for /F %%i in ('git rev-parse --abbrev-ref HEAD') do ( set branch_id=%%i)
echo build_app = %app% > ./src/main/resources/build.properties
echo build_time = %time% >> ./src/main/resources/build.properties
echo git_commit = %commit_id% >> ./src/main/resources/build.properties
echo git_branch = %branch_id% >> ./src/main/resources/build.properties
echo start %app% at %date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%
::echo "build_version = '${version}'" > ./src/main/resources/build.properties
::echo "upload %app%"
::echo "done %app% %time% at $(date "+%Y-%m-%d %H:%M:%S")"
