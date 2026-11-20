package br.com.infnet.missioncontrol.model;

import jakarta.annotation.Nullable;

import java.util.Collection;

public record ValidationResponse(Collection<String> messages, boolean isOk) {
}
