#!/bin/bash
CONFIG_SERVER_BASE_URL=${CONFIG_SERVER_BASE_URL:?"need env param [CONFIG_SERVER_BASE_URL]"}
APOLLO_APP_ID=${APOLLO_APP_ID:?"need env param [APOLLO_APP_ID]"}
PUBLIC_PREFIX=${PUBLIC_PREFIX:-"PLATFORM"}
APOLLO_CLUSTER=${APOLLO_CLUSTER:-"default"}
CONFIG_FILES="$(find /data/tomcat/webapps/ROOT/WEB-INF/classes/ -name "*.properties" ! -path "*static*"  | awk -vFS="/" '{print $NF}')"
ECS_IP=$(curl http://100.100.100.200/latest/meta-data/private-ipv4)
APOLLO_FORCE_PROPERTIES=${APOLLO_FORCE_PROPERTIES:-"true"}


for _config_file in ${CONFIG_FILES[@]}
do
    export result=
    echo "## $_config_file"
    _abs_config_file_path=`find /data/tomcat/webapps/ROOT/WEB-INF/classes/ -name "$_config_file" ! -path "*static*"`
    _namespace=${_config_file%%.*}

    if [ x"$_namespace" = x"config" ] || [ x"$_namespace" = x"db-config" ];then
        _namespace="${PUBLIC_PREFIX}.${_namespace}"
    fi

    result=$(curl -s ${CONFIG_SERVER_BASE_URL}/configfiles/${APOLLO_APP_ID}/${APOLLO_CLUSTER}/${_namespace}?ip=${ECS_IP} | tr -d '\\' )
    if echo $result | grep -q 'Not Found' ;then
        echo "#################        $_config_file not found in apollo!!!      #######################"  
        if [ x"$APOLLO_FORCE_PROPERTIES" = x"true" ];then
            exit 404
        fi
        continue
    fi
    echo "### init  ${_abs_config_file_path} ..."
    echo "$result" | sort  > ${_abs_config_file_path}
done