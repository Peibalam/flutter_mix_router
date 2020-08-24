// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'route_info.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

RouteInfo _$RouteInfoFromJson(Map<String, dynamic> json) {
  return RouteInfo(
    url: json['url'] as String,
    key: json['key'] as int,
    index: json['index'] as int,
  );
}

Map<String, dynamic> _$RouteInfoToJson(RouteInfo instance) => <String, dynamic>{
      'key': instance.key,
      'index': instance.index,
      'url': instance.url,
    };

RouteInfoList _$RouteInfoListFromJson(Map<String, dynamic> json) {
  return RouteInfoList(
    list: (json['flutterRouteInfo'] as List)
        .map((e) => RouteInfo.fromJson(e as Map<String, dynamic>))
        .toList(),
  );
}

Map<String, dynamic> _$RouteInfoListToJson(RouteInfoList instance) =>
    <String, dynamic>{
      'flutterRouteInfo': instance.list.map((e) => e.toJson()).toList(),
    };
