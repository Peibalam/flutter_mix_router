import 'dart:collection';
import 'dart:convert';
import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_module/channel/platform_channel.dart';
import 'package:flutter_module/main.dart';
import 'package:flutter_module/mixroute/route_info.dart';
import 'package:flutter_module/mixroute/simple_route.dart';

class MixRouteHelper {
  static const nativeMainRoute = 'tt://MainActivity';
  static const nativeSecondRoute = 'tt://SecondActivity';
  static const nativeThreeRoute = 'tt://ThreeActivity';

  static const _mixRouteChannel =
      const MethodChannel('samples.flutter.dev/ttMixRoute');
  static const _METHOD_MIX_ROUTE = "mixRoute";
  static const _METHOD_PUSH_FLUTTER = "pushFlutter";
  static const _METHOD_CLOSE_FLUTTER_PAGE = "closeFlutterPage";
  static const _METHOD_POP_FLUTTER = "popFlutter";
  static const KEY_ROUTE_INFO = "flutterRouteInfo";

  static GlobalKey<NavigatorState> navigatorKey = GlobalKey<NavigatorState>();

  static Map<String, Route> _flutterRoutes = HashMap();

  static Future<void> _routeNative(String url, bool clearTop) async {
    try {
      await _mixRouteChannel.invokeMethod(_METHOD_MIX_ROUTE,
          <String, dynamic>{'url': url, 'clearTop': clearTop});
    } on PlatformException catch (e) {
      log('_navigator fail $e');
    }
  }

  static Future<void> closeFlutterPage() async {
    try {
      await _mixRouteChannel.invokeMethod(_METHOD_CLOSE_FLUTTER_PAGE);
    } on PlatformException catch (e) {
      log('closeFlutterPage fail $e');
    }
  }

  static void push(String url, {bool clearTop = false}) {
    _routeNative(url, clearTop);
//    Navigator.pushNamed(
//      AppHelper.appContext,
//      url,
//    );
  }

  static void init() {
    log('MixRouteHelper init');
    _mixRouteChannel.setMethodCallHandler((handler) {
      _handleMixRouteChannel(handler);
      return;
    });
  }

  static void _handleMixRouteChannel(MethodCall handler) {
    switch (handler.method) {
      case _METHOD_PUSH_FLUTTER:
        _onPushFlutter(handler);
        return;

      case _METHOD_POP_FLUTTER:
        _onPopFlutter(handler);
        return;

      default:
        log('unSupportMethod ${handler.method}');
        return;
    }
  }

  static void _onPopFlutter(MethodCall handler) {
    var arguments = handler.arguments;
    if (arguments is String) {
      var routeInfoList = RouteInfoList.fromJson(jsonDecode(arguments));
      routeInfoList.list.forEach((routeInfo) {
        var route = _flutterRoutes.remove(routeInfo.getUniqueKey());
        if (route != null) {
//          if (routeInfo.isRootIndex()) {
//            //避免返回时黑屏
//            Future<void>.delayed(Duration(seconds: 1), () {
//              navigatorKey.currentState.removeRoute(route);
//            });
//          } else {
//            navigatorKey.currentState.removeRoute(route);
//          }
          //navigatorKey.currentState.removeRoute(route);
          navigatorKey.currentState.pop();

          log('popFlutter $route');
        } else {
          log('popFlutter absent ${routeInfo.toJson()}');
        }

        //navigatorKey.currentState.popUntil((route) => routeInfoList.list.last.getName() == route.settings.name);
        //navigatorKey.currentState.pop();
      });

      var routes = '';
      _flutterRoutes.values.forEach((element) {
        routes = routes + "${element.settings}, ";
      });
      log('popFlutter after $routes');
    }
  }

  static void _onPushFlutter(MethodCall handler) {
    var arguments = handler.arguments;
    if (arguments is String) {
      var routeInfoList = RouteInfoList.fromJson(jsonDecode(arguments));
      //var routeModel = Provider.of<RouteModel>(AppHelper.appContext, listen: false);
      //routeModel.changeRoute(url);
      log('pushFlutter routeInfo ${routeInfoList.toJson()}');
      routeInfoList.list.forEach((routeInfo) {
        if (!_flutterRoutes.containsKey(routeInfo.getUniqueKey())) {
          var route = MaterialPageRoute(
              settings: RouteSettings(
                  name: routeInfo.getName(), arguments: routeInfo),
              builder: (context) => _getWidget(routeInfo.url));
          _flutterRoutes[routeInfo.getUniqueKey()] = route;
          MixRouteHelper.navigatorKey.currentState.push(route);
        } else {
          log('pushFlutter already exist ${routeInfo.toJson()}');
        }
      });
    }
  }

  static Widget _getWidget(String url) {
    switch (url) {
      case MyHomePage.routeUrl:
        return MyHomePage();
      case FirstRoute.routeUrl:
        return FirstRoute();
      case SecondRoute.routeUrl:
        return SecondRoute();
      case ThreeRoute.routeUrl:
        return ThreeRoute();
      case FourRoute.routeUrl:
        return FourRoute();
      case FiveRoute.routeUrl:
        return FiveRoute();
      case SixRoute.routeUrl:
        return SixRoute();
      default:
        return Container(child: Text('DefaultHomePage'));
    }
  }
}
