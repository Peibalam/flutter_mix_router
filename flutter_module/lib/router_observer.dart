

import 'package:flutter/material.dart';
import 'package:flutter_module/channel/platform_channel.dart';

class MyRouterObserver extends NavigatorObserver {

  var _routerCount = 0;

  void didPush(Route<dynamic> route, Route<dynamic> previousRoute) {
    super.didPush(route, previousRoute);
    log('didPush route ${route.settings}, previousRoute ${previousRoute?.settings}');
    _routerCount++;
  }

  void didPop(Route<dynamic> route, Route<dynamic> previousRoute) {
    super.didPop(route, previousRoute);
    log('didPop route ${route.settings}, previousRoute ${previousRoute?.settings}');
    _routerCount--;
  }

  void didRemove(Route<dynamic> route, Route<dynamic> previousRoute) {
    super.didRemove(route, previousRoute);
    log('didRemove route ${route.settings}, previousRoute ${previousRoute?.settings}');
  }

  void didReplace({Route<dynamic> newRoute, Route<dynamic> oldRoute}) {
    super.didReplace(newRoute: newRoute, oldRoute: oldRoute);
    log('didReplace newRoute ${newRoute.settings}, oldRoute ${oldRoute?.settings}');
  }

  void didStartUserGesture(Route<dynamic> route, Route<dynamic> previousRoute) {
    super.didStartUserGesture(route, previousRoute);
    log('didStartUserGesture route ${route.settings}, previousRoute ${previousRoute?.settings}');
  }

  @override
  void didStopUserGesture() {
    log('didStopUserGesture');
  }
}