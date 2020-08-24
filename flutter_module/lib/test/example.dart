// Copyright (c) 2017, the Dart project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

import 'package:json_annotation/json_annotation.dart';

part 'example.g.dart';

//Address 为了得到正常的输出，你需要在类声明之前为 @JsonSerializable 方法加入 explicitToJson: true 参数
@JsonSerializable(nullable: false, explicitToJson: true)
class Person {
  final String firstName;
  final String lastName;
  final DateTime dateOfBirth;
  final Address address;

  Person({this.firstName, this.lastName, this.dateOfBirth, this.address});

  factory Person.fromJson(Map<String, dynamic> json) => _$PersonFromJson(json);

  Map<String, dynamic> toJson() => _$PersonToJson(this);
}

@JsonSerializable()
class Address {
  String street;
  String city;

  Address(this.street, this.city);

  factory Address.fromJson(Map<String, dynamic> json) => _$AddressFromJson(json);
  Map<String, dynamic> toJson() => _$AddressToJson(this);
}
