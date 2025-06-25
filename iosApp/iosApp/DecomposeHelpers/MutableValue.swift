//
//  MutableValue.swift
//  iosApp
//
//  Created by Айдан Галеев on 23.06.2025.
//
import ComposeApp

// Source: https://github.com/arkivanov/Decompose/blob/master/sample/app-ios/app-ios/DecomposeHelpers/MutableValue.swift
func mutableValue<T: AnyObject>(_ initialValue: T) -> MutableValue<T> {
    return MutableValueBuilderKt.MutableValue(initialValue: initialValue) as! MutableValue<T>
}
