

@echo off
cls
set loopcount=0
:loop
set /A loopcount=loopcount+1
echo  ************************%loopcount% Runs!************************
git pull
call gradlew.bat assembleDebug
adb push app\build\outputs\apk\debug\app-debug.apk /data/local/tmp/com.eligasht
adb shell pm install -t -r "/data/local/tmp/com.eligasht"
adb shell am instrument -w -r   -e debug false -e class 'com.eligasht.reservation.views.test.FullTest#runTest' com.eligasht.test/android.support.test.runner.AndroidJUnitRunner
goto loop
:exitloop
pause






