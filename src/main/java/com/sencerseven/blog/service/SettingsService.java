package com.sencerseven.blog.service;

import com.sencerseven.blog.command.SettingsCommand;
import com.sencerseven.blog.domain.Settings;

public interface SettingsService {

    SettingsCommand findById(long id);

    SettingsCommand saveCommand(SettingsCommand settingsCommand);
}
