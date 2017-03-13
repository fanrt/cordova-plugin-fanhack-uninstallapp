"use strict";

var cordova = require('cordova');  
  
var UninstallOtherApp = function() {};  
  
UninstallOtherApp.prototype.uninstall = function(uri, success, error) {
    cordova.exec(success, error, 'UninstallOtherAppPlugin', 'uninstall', [uri]);
};

UninstallOtherApp.prototype.checkAppVersion = function(uri, version, success, error) {
    cordova.exec(success, error, 'UninstallOtherAppPlugin', 'checkAppVersion', [uri, version]);
};

var uninstallOtherApp = new UninstallOtherApp();  
module.exports = uninstallOtherApp; 