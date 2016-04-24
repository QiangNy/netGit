//
// Created by chen on 16-4-24.
//

#include "include/MiniCore/LOG/MLog.h"
#include <stdio.h>


void DebugMessage(JNIEnv* env,jobject thiz,int lever,const char* classname) {
    LOGI("it's my jni test");
}

