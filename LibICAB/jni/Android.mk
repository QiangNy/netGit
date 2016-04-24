
LOCAL_PATH	:= $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE	:=cylacloudjni
LOCAL_SRC_FILES :=src/jni.c \
    src/MLog.c

#Force strict code checking
LOCAL_CFLAGS	+= -Werror -Wall
LOCAL_LDLIBS    := -lm -llog
include $(BUILD_SHARED_LIBRARY)