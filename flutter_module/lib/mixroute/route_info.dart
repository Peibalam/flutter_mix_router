// Copyright (c) 2017, the Dart project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

import 'package:json_annotation/json_annotation.dart';

import 'mix_route_helper.dart';

part 'route_info.g.dart';

@JsonSerializable(nullable: false, explicitToJson: true)
class RouteInfo {

  @JsonKey(name: 'key')
  final int key;
  @JsonKey(name: 'index')
  final int index;
  @JsonKey(name: 'url')
  final String url;

  RouteInfo({this.url, this.key, this.index});

  factory RouteInfo.fromJson(Map<String, dynamic> json) => _$RouteInfoFromJson(json);

  Map<String, dynamic> toJson() => _$RouteInfoToJson(this);

  String getUniqueKey() {
    return '$key|$index';
  }

  String getName() {
    return "${getUniqueKey()}|$url";
  }

  bool isRootIndex() {
    return index == 0;
  }



}

@JsonSerializable(nullable: false, explicitToJson: true)
class RouteInfoList {
  @JsonKey(name: MixRouteHelper.KEY_ROUTE_INFO)
  final List<RouteInfo> list;

  RouteInfoList({this.list});

  factory RouteInfoList.fromJson(Map<String, dynamic> json) => _$RouteInfoListFromJson(json);

  Map<String, dynamic> toJson() => _$RouteInfoListToJson(this);

}