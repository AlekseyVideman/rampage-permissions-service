package com.rampagemc.permissions.service.migration;

import com.rampagemc.permissions.domain.entity.LuckPermsCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LuckPermsServiceImpl implements LuckPermsService {

    private final WebClient webClient;

    @Override
    @SuppressWarnings("all")
    public boolean execute(@NonNull LuckPermsCommand luckPermsCommand) {
        Mono<Boolean> result = webClient.post()
                .bodyValue(luckPermsCommand.getCommand())
                .exchangeToMono(response ->
                        response.statusCode().equals(HttpStatus.OK) ? Mono.just(true) : Mono.just(false));

        return result.block();
    }
}
