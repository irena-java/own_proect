package com.datascience.shop.controller;

import com.datascience.shop.Rate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShowRatesController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(ShowRatesController.class);
    private String ratesTxt;

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        final Logger logger = LoggerFactory.getLogger(ShowRatesController.class);

        try {
            URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
            Scanner scanner = new Scanner(url.openStream());
            StringBuffer stringBuffer = new StringBuffer();
            while (scanner.hasNext()) {
                stringBuffer.append(scanner.next());
            }
            ratesTxt = stringBuffer.toString();
            ratesTxt = ratesTxt.replaceAll("<[^>]*>", "");
        } catch (IOException e) {
            logger.error("Failed url in ShowRatesController" + e);
            return new ControllerResultDto(VIEW_SERVER_ERROR);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Rate> rates = Arrays.asList(objectMapper.readValue(ratesTxt, Rate[].class));
            req.setAttribute(REQUEST_ATTRIBUTE_RATES, rates);
            return new ControllerResultDto(VIEW_RATES);
        } catch (JsonProcessingException e) {
            logger.error("Failed json in ShowRatesController" + e);
            return new ControllerResultDto(VIEW_SERVER_ERROR);
        }
    }
}