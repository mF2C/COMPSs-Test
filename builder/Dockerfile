FROM mf2c/compss-agent:latest

COPY ["arrel", "/"]
ENV PORT=46100
CMD /opt/COMPSs/launchers/launch_dataclay.sh -h ${MF2C_HOST} -p ${PORT} -d ${DEBUG} -ra ${REPORT_ADDRESS} -lm ${LOGICMODULE_EP}
