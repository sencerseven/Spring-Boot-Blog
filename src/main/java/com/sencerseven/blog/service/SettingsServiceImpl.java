package com.sencerseven.blog.service;

import com.sencerseven.blog.command.SettingsCommand;
import com.sencerseven.blog.converter.SettingsCommandToSettingsConverter;
import com.sencerseven.blog.converter.SettingsToSettingsCommandConverter;
import com.sencerseven.blog.domain.Settings;
import com.sencerseven.blog.repository.SettingsRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingsServiceImpl implements SettingsService {


    SettingsRepository settingsRepository;
    SettingsToSettingsCommandConverter settingsToSettingsCommandConverter;
    SettingsCommandToSettingsConverter settingsCommandToSettingsConverter;


    public SettingsServiceImpl(SettingsRepository settingsRepository, SettingsToSettingsCommandConverter settingsToSettingsCommandConverter, SettingsCommandToSettingsConverter settingsCommandToSettingsConverter) {
        this.settingsRepository = settingsRepository;
        this.settingsToSettingsCommandConverter = settingsToSettingsCommandConverter;
        this.settingsCommandToSettingsConverter = settingsCommandToSettingsConverter;
    }

    @Override
    @Cacheable("settingsCommand")
    public SettingsCommand findById(long id) {
        Optional<Settings> settingsOptional =settingsRepository.findById(id);
        if(!settingsOptional.isPresent())
            return new SettingsCommand();

        return settingsToSettingsCommandConverter.convert(settingsOptional.get());
    }

    @Override
    public SettingsCommand saveCommand(SettingsCommand settingsCommand) {
        Settings settings = settingsCommandToSettingsConverter.convert(settingsCommand);

        Settings tempSettings = settingsRepository.save(settings);

        return settingsToSettingsCommandConverter.convert(tempSettings);

    }
}
