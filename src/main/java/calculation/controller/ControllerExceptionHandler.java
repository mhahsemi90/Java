package calculation.controller;

import calculation.configuration.PcnExceptionConfiguration;
import calculation.services.exception.CalculationException;
import graphql.GraphQLError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler {
    private final ControllerExceptionMessageCreation controllerExceptionMessageCreation;
    private final PcnExceptionConfiguration pcnExceptionConfiguration;

    @GraphQlExceptionHandler
    public GraphQLError handle(Exception ex) {
        Map<String, Object> extensions = new LinkedHashMap<>();
        String s = ex.getClass().getSimpleName();
        String message = ex.getMessage();
        message = message != null ? (s + ": " + message) : s;
        if (pcnExceptionConfiguration.getShowInResponse()) {
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                if (stackTraceElement.getClassName().contains(".pcn.")) {
                    String methodName = stackTraceElement.getMethodName();
                    String className = stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf(".") + 1);
                    String lineNumber = String.valueOf(stackTraceElement.getLineNumber());
                    extensions.put(
                            methodName + " : " + className + " " + lineNumber
                            , message);
                }
            }
        }
        if (pcnExceptionConfiguration.getLogInConsole()) {
            log.error(message);
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                if (stackTraceElement.getClassName().contains(".pcn.")) {
                    String methodName = stackTraceElement.getMethodName();
                    String className = stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf(".") + 1);
                    String lineNumber = String.valueOf(stackTraceElement.getLineNumber());
                    log.error(methodName + " : " + className + " " + lineNumber);
                }
            }
        }
        return GraphQLError
                .newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .extensions(extensions)
                .message(controllerExceptionMessageCreation.getMessage(ex))
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(CalculationException ex) {
        return GraphQLError
                .newError()
                .errorType(ex.getErrorType())
                .message(controllerExceptionMessageCreation.getMessage(ex))
                .build();
    }
}
