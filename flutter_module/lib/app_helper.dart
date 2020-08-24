
import 'package:flutter/widgets.dart';
import 'package:flutter_module/channel/platform_channel.dart';

class AppHelper {

  static BuildContext appContext;

  static void init(BuildContext context) {
    if (appContext == null) {
      log("logFlutterContext");
      appContext = context;
    }
  }

}