
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_module/channel/mix_route_helper.dart';
import 'package:flutter_module/main.dart';

import 'mix_route_helper.dart';

class FirstRoute extends StatefulWidget {

  static const String routeUrl = 'flutter/first';

  @override
  _FirstRouteState createState() {
    return _FirstRouteState();
  }

}

class _FirstRouteState extends State<FirstRoute> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('First Route'),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            RaisedButton(
              child: Text('Open route'),
              onPressed: () {
                MixRouteHelper.push(SecondRoute.routeUrl);
              },
            ),
            RaisedButton(
              onPressed: () {
                Navigator.pop(context);
              },
              child: Text('pop'),
            ),
            RaisedButton(
              onPressed: () {
                MixRouteHelper.push(MyHomePage.routeUrl, clearTop: true);
              },
              child: Text('clearTop toFlutterHome'),
            ),
            RaisedButton(
              onPressed: () {
                MixRouteHelper.push(MixRouteHelper.nativeMainRoute, clearTop: true);
              },
              child: Text('clearTop toNativeMain'),
            )
          ],
        ),
      ),
    );
  }
}

class SecondRoute extends StatelessWidget {

  static const String routeUrl = 'flutter/second';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Second Route"),
      ),
      body: Center(
        child: Column(children: <Widget>[
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MixRouteHelper.nativeMainRoute);
            },
            child: Text('openNativeMainAct'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(ThreeRoute.routeUrl);
            },
            child: Text('open route'),
          ),
          RaisedButton(
            onPressed: () {
            Navigator.pop(context);
            },
            child: Text('pop'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MyHomePage.routeUrl, clearTop: true);
            },
            child: Text('clearTop toFlutterHome'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(FirstRoute.routeUrl, clearTop: true);
            },
            child: Text('clearTop toFlutterFirst'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MixRouteHelper.nativeMainRoute, clearTop: true);
            },
            child: Text('clearTop toNativeMain'),
          )
        ],),
      ),
    );
  }
}

class ThreeRoute extends StatelessWidget {

  static const String routeUrl = 'flutter/three';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Three Route"),
      ),
      body: Center(
        child: Column(children: <Widget>[
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MixRouteHelper.nativeSecondRoute);
            },
            child: Text('openNativeSecondAct'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(FourRoute.routeUrl);
            },
            child: Text('open route'),
          ),
          RaisedButton(
            onPressed: () {
              Navigator.pop(context);
            },
            child: Text('pop'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MyHomePage.routeUrl, clearTop: true);
            },
            child: Text('clearTop toFlutterHome'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(FirstRoute.routeUrl, clearTop: true);
            },
            child: Text('clearTop toFlutterFirst'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MixRouteHelper.nativeMainRoute, clearTop: true);
            },
            child: Text('clearTop toNativeMain'),
          )
        ],),
      ),
    );
  }
}

class FourRoute extends StatelessWidget {

  static const String routeUrl = 'flutter/four';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("FourRoute Route"),
      ),
      body: Center(
        child: Column(children: <Widget>[
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MixRouteHelper.nativeThreeRoute);
            },
            child: Text('openNativeThreeAct'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(FiveRoute.routeUrl);
            },
            child: Text('open route'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(FirstRoute.routeUrl, clearTop: true);
            },
            child: Text('clearTop toFlutterFirst'),
          ),
        ],),
      ),
    );
  }
}


class FiveRoute extends StatelessWidget {

  static const String routeUrl = 'flutter/five';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("five Route"),
      ),
      body: Center(
        child: Column(children: <Widget>[
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MixRouteHelper.nativeThreeRoute);
            },
            child: Text('openNativeThree'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(SixRoute.routeUrl);
            },
            child: Text('open route'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(FirstRoute.routeUrl, clearTop: true);
            },
            child: Text('clearTop toFlutterFirst'),
          ),
          RaisedButton(
            onPressed: () {
              Navigator.pop(context);
            },
            child: Text('pop'),
          )
        ],),
      ),
    );
  }
}

class SixRoute extends StatelessWidget {

  static const String routeUrl = 'flutter/six';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("six Route"),
      ),
      body: Center(
        child: Column(children: <Widget>[
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(MixRouteHelper.nativeThreeRoute);
            },
            child: Text('openNativeThree'),
          ),
          RaisedButton(
            onPressed: () {
              MixRouteHelper.push(FirstRoute.routeUrl, clearTop: true);
            },
            child: Text('clearTop toFlutterFirst'),
          ),
          RaisedButton(
            onPressed: () {
              Navigator.pop(context);
            },
            child: Text('pop'),
          )
        ],),
      ),
    );
  }
}