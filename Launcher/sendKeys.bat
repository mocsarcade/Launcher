@if (@X)==(@Y) @end /* JScript comment 
        @echo off 
       
        rem :: the first argument is the script name as it will be used for proper help message 
        cscript //E:JScript //nologo "%~f0" "%~nx0" %* 
        exit /b %errorlevel% 
@if (@X)==(@Y) @end JScript comment */ 


var sh=new ActiveXObject("WScript.Shell"); 
var ARGS = WScript.Arguments; 

var title="";
var keys="";

title=ARGS.Item(1);

WScript.Sleep(2000);
while(!sh.AppActivate(title)) {
    sh.AppActivate(title)
    WScript.Sleep(1);
    WScript.Echo("Opening " + title);
}