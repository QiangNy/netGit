//
// Created by chen on 16-4-24.
//

#ifndef CYLA_MLOG_H
#define CYLA_MLOG_H

#include <jni.h>
#include <android/log.h>


#define  LOGSWITCH 1
#define JNI_FORMAT "JNI  %s  "
#define JNI_INFO __FUNCTION__
#define LOG_TAG "icabjni"

#define  LOGI(...)  {if (LOGSWITCH){__android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__);}}
//#define LOGA(x) {if (LOGSWITCH){DumpDebugMessage x;}}

#if defined(__cplusplus)
extern "C"
{
#endif

void DebugMessage(JNIEnv* env,jobject thiz,int lever,const char* classname);

#if defined(__cplusplus)
};
#endif

#endif //CYLA_MLOG_H
