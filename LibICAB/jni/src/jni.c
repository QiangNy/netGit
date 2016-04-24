//
// Created by chen on 16-4-24.
//
#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <jni.h>
#include <string.h>
#include <assert.h>
#include "include/MiniCore/LOG/MLog.h"
/**
*@param function name in juava
*@param type
*@param function name in c
*/
static JNINativeMethod gMethods[] = {
    {"nativesmartLog", "(ILjava/lang/String;)V", (void*)DebugMessage},//绑定
};

/*
* 为某一个类注册本地方法
*/
static int registerNativeMethods(JNIEnv* env, const char* className, JNINativeMethod* gMethods, int numMethods) {
    jclass clazz;
    clazz = (*env)->FindClass(env, className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }

    if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/*
* 为所有类注册本地方法
*/
static int registerNatives(JNIEnv* env) {
    int res = 0;
    const char* kClassName = "jni/juan/natives/CyLalib";//指定要注册的类
    res = registerNativeMethods(env, kClassName, gMethods,
            sizeof(gMethods) / sizeof(gMethods[0]));
    return res;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {


    JNIEnv* env = NULL;
    jint result = -1;

    if ((*vm) -> GetEnv(vm, (void**) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    assert(env != NULL);

    if (!registerNatives(env)) {
        return -1;
    }

    result = JNI_VERSION_1_6;
    return result;
}
