package com.crypto.core.telegram.services;

import com.crypto.core.telegram.entity.MessageEntity;
import com.pengrad.telegrambot.ExceptionHandler;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;

public interface TelegramBotService {
    void setUpdateListener(UpdatesListener updatesListener, ExceptionHandler exceptionHandler);

    void removeUpdateListener();

    void deleteAllMessages(Long chatId);

    SendResponse sendMessage(Long chatId, String text);

    SendResponse sendMessage(Long chatId, String text, ParseMode parseMode);

    BaseResponse updateMessage(Long chatId, Integer messageId, String text);

    BaseResponse updateMessage(Long chatId, Integer messageId, String text, ParseMode parseMode);

    BaseResponse deleteMessage(Long chatId, Integer messageId);

    MessageEntity saveMessage(Message message);
}