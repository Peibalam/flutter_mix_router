

import 'package:flutter/material.dart';
import 'package:flutter_module/test/test.dart';

class App extends StatefulWidget {

  @override
  _AppState createState() {
    return _AppState();
  }

}

class _AppState extends State<App> {

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "my app",
      home: TutorialHome(),
    );
  }

  @override
  void dispose() {
    super.dispose();
  }
}