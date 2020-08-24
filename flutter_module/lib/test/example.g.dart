// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'example.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Person _$PersonFromJson(Map<String, dynamic> json) {
  return Person(
    firstName: json['firstName'] as String,
    lastName: json['lastName'] as String,
    dateOfBirth: DateTime.parse(json['dateOfBirth'] as String),
    address: Address.fromJson(json['address'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$PersonToJson(Person instance) => <String, dynamic>{
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'dateOfBirth': instance.dateOfBirth.toIso8601String(),
      'address': instance.address.toJson(),
    };

Address _$AddressFromJson(Map<String, dynamic> json) {
  return Address(
    json['street'] as String,
    json['city'] as String,
  );
}

Map<String, dynamic> _$AddressToJson(Address instance) => <String, dynamic>{
      'street': instance.street,
      'city': instance.city,
    };
