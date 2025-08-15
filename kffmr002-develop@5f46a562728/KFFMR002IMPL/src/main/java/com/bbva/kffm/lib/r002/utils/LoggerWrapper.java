package com.bbva.kffm.lib.r002.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class LoggerWrapper {

    public enum Level {
        TRACE, DEBUG, INFO, WARNING, ERROR
    }

    protected LoggerWrapper() {
    }

    /**
     * METODO ENCARGADO DE MOSTRAR UN MENSAJE EN TRAZA
     *
     * @param level      Nivel de traza
     * @param mensaje    Mensaje a enviar a traza
     * @param claseLanza Clase que pinta la traza
     */
    public static void writeCustomMessage(Level level, String mensaje, Class<?> claseLanza) {

        Logger logger = LoggerFactory.getLogger(claseLanza);

        String mensajeLog = null;

        if (level != null) {
            mensajeLog = MessageFormat.format("[ {0} ] - {1}", level.name(), mensaje);
        } else {
            logger.info("[ERROR] - SE HA PRODUCIDO UN ERROR AL LANZAR EL MENSAJE A TRAZA");
        }

        if (Level.TRACE.equals(level)) {
            logger.trace(mensajeLog);
        } else if (Level.DEBUG.equals(level)) {
            logger.debug(mensajeLog);
        } else if (Level.INFO.equals(level) || Level.WARNING.equals(level) || Level.ERROR.equals(level)) {
            logger.info(mensajeLog);
        }

    }

    public static void infoCorrectInvocation(String componente, Class<?> claseLanza) {
        LoggerWrapper.writeCustomMessage(Level.INFO,
                MessageFormat.format("INVOCACION A [ {0} ] REALIZADA CORRECTAMENTE", componente), claseLanza);
    }

    public static void infoMapInComponent(String componente, Object entrada, Class<?> claseLanza) {
        LoggerWrapper.writeCustomMessage(Level.INFO,
                MessageFormat.format("INVOCACION A [ {0} ] - MAPEO DE ENTRADA - [ {1} ]", componente, entrada),
                claseLanza);
    }

    public static void infoMapOutComponent(String componente, Object salida, Class<?> claseLanza) {
        LoggerWrapper.writeCustomMessage(Level.INFO,
                MessageFormat.format("INVOCACION A [ {0} ] - MAPEO DE SALIDA - [ {1} ]", componente, salida),
                claseLanza);
    }

    public static void infoInputComponent(String componente, Object entrada, Class<?> claseLanza) {
        LoggerWrapper.writeCustomMessage(Level.INFO,
                MessageFormat.format("INVOCACION A [ {0} ] - ENTRADA - [ {1} ]", componente, entrada), claseLanza);
    }

    public static void infoOutputComponent(String componente, Object salida, Class<?> claseLanza) {
        LoggerWrapper.writeCustomMessage(Level.INFO,
                MessageFormat.format("INVOCACION A [ {0} ] - SALIDA - [ {1} ]", componente, salida), claseLanza);
    }

}
