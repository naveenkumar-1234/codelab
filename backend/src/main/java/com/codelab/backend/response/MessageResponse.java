package com.codelab.backend.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageResponse {
    String message;
}
