import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/app_helper.dart';
import 'package:flutter_module/channel/platform_channel.dart';
import 'package:flutter_module/router_observer.dart';
import 'package:flutter_module/simple_route.dart';
import 'package:provider/provider.dart';

import 'mixroute/mix_route_helper.dart';
import 'mixroute/simple_route.dart';

void main() {
  FocusNode _backNode = new FocusNode();
  runApp(MultiProvider(
    providers: [
      ChangeNotifierProvider(create: (context) => RouteModel()),
    ],
    child: RawKeyboardListener(
      focusNode: _backNode,
      child: MyApp(),
      onKey: (event) {
        if (event.runtimeType.toString() == 'RawKeyDownEvent' &&
            event.data is RawKeyEventDataAndroid) {
          RawKeyEventDataAndroid data = event.data;
          //获取到keyCode进行判断,4就是返回键
          if (data.keyCode == 4) {
            //执行相应的逻辑,回到上层或者pop
            log('onBackPress');
//            MixRouteHelper.pop();
            return false;
          }
        }
        return false;
      },
    ),
  ));

//  runApp(MultiProvider(
//    providers: [
//      ChangeNotifierProvider(create: (context) => RouteModel()),
//    ],
//    child: WillPopScope(
//      child: MyApp(),
//      onWillPop: () async {
//    // You can do some work here.
//    // Returning true allows the pop to happen, returning false prevents it.
//        log('onBackPressed');
//    return true;
//    },
//    ),
//  ));
}

class MyApp extends StatefulWidget {
  @override
  MyAppState createState() {
    return MyAppState();
  }
}

class RouteModel extends ChangeNotifier {
  String _route = '';

  void changeRoute(String route) {
    _route = route;
    notifyListeners();
  }

  String getRoute() {
    return _route;
  }
}

class MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    AppHelper.init(context);
    MixRouteHelper.init();
    log('MyApp initState');
  }

  @override
  Widget build(BuildContext context) {
    log('build MyApp');

//    Future<void>.delayed(Duration(seconds: 3), () {
//      var routeModel = Provider.of<RouteModel>(AppHelper.appContext, listen: false);
//      log('routeModel $routeModel');
//    });

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomeBlankWidget(),
      navigatorKey: MixRouteHelper.navigatorKey,
      routes: _buildRoutes(),
      navigatorObservers: [MyRouterObserver()],
    );
  }

  Map<String, WidgetBuilder> _buildRoutes() {
    return {
      MyHomePage.routeUrl: (context) => MyHomePage(),
      FirstRoute.routeUrl: (context) => FirstRoute(),
      SecondRoute.routeUrl: (context) => SecondRoute(),
      ThreeRoute.routeUrl: (context) => ThreeRoute(),
      FourRoute.routeUrl: (context) => FourRoute(),
      FiveRoute.routeUrl: (context) => FiveRoute(),
      SixRoute.routeUrl: (context) => SixRoute(),
    };
  }

  Widget _handleRoutes(String url) {
    log('_handleRoutes $url');
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

class HomeBlankWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    log('build HomeBlankWidget');

    return Container(
        decoration: BoxDecoration(color: Colors.transparent), child: Text(''));
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key}) : super(key: key);

  static const String routeUrl = 'flutter/home';

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text('MyHomePage'),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            GestureDetector(
              child: Text('openFlutterPage'),
              onTap: () {
                MixRouteHelper.push(FirstRoute.routeUrl);
              },
            ),
            Container(
              padding: EdgeInsets.fromLTRB(100, 100, 100, 100),
              child: GestureDetector(
                child: Text('openNativeMainPage'),
                onTap: () {
                  MixRouteHelper.push(MixRouteHelper.nativeMainRoute);
                },
              ),
            ),
            RaisedButton(
              onPressed: () {
                Navigator.pop(context);
              },
              child: Text('pop'),
            ),
            RaisedButton(
              onPressed: () {
                MixRouteHelper.closeFlutterPage();
              },
              child: Text('closeFlutter'),
            ),
            RaisedButton(
              onPressed: () {
                MixRouteHelper.push(FirstRoute.routeUrl, clearTop: true);
              },
              child: Text('clearTopToFirstRoute'),
            ),
            RaisedButton(
              onPressed: () {
                MixRouteHelper.push(SecondRoute.routeUrl, clearTop: true);
              },
              child: Text('clearTopToSecondRoute'),
            ),
            RaisedButton(
              onPressed: () {
                MixRouteHelper.push(ThreeRoute.routeUrl, clearTop: true);
              },
              child: Text('clearTopToThreeRoute'),
            )
          ],
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  @override
  void dispose() {
    super.dispose();
    log('_MyHomePageState dispose');
  }
}
