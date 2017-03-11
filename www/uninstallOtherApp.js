"use strict";

var cordova = require('cordova');  
  
var UninstallOtherApp = function() {};  
  
UninstallOtherApp.prototype.uninstall = function(uri, version,success, error) {
    cordova.exec(success, error, 'UninstallOtherAppPlugin', 'uninstall', [uri, version]);
};  
  
var uninstallOtherApp = new UninstallOtherApp();  
module.exports = uninstallOtherApp; 