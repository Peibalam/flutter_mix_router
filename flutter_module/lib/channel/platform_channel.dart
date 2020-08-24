
import 'package:flutter/services.dart';

const logPlatform = const MethodChannel('samples.flutter.dev/log');

Future<void> _logImpl(String str) async {
  print('$str');
  try {
    await logPlatform.invokeMethod('log', 'flutterLog $str');
  } on PlatformException catch (e) {

  }
}

log(String str) async {
  var future = _logImpl(str);
  future.catchError((e, s) {

  });
}





