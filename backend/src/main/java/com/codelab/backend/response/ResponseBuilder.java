    package com.codelab.backend.response;

    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import java.time.Instant;


    public class ResponseBuilder {

        public static  <T> ResponseEntity<ApiResponse<T>> success(T data){
            return ResponseEntity.ok(
                    ApiResponse.<T>builder()
                            .success(true)
                            .status(HttpStatus.OK.value())
                            .message("Operation successful")
                            .data(data)
                            .timestamp(Instant.now().toString())
                            .build()
            );
        }

        public static <T> ResponseEntity<ApiResponse<T>> success(T data,String message){
            return ResponseEntity.ok(
                    ApiResponse.<T>builder()
                            .success(true)
                            .status(HttpStatus.OK.value())
                            .message(message)
                            .data(data)
                            .timestamp(Instant.now().toString())
                            .build()
            );
        }

        public static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.<T>builder()
                            .success(true)
                            .status(HttpStatus.CREATED.value())
                            .message(message)
                            .data(data)
                            .timestamp(Instant.now().toString())
                            .build()
            );
        }

        public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatus status, String message, T details) {
            return ResponseEntity.status(status).body(
                    ApiResponse.<T>builder()
                            .success(false)
                            .status(status.value())
                            .message(message)
                            .data(details)
                            .timestamp(Instant.now().toString())
                            .build()
            );
        }

    //    public static <T> ResponseEntity<PaginatedApiResponse<T>> paginated(List<T> content, Pageable pageable,long totalElements){
    //        return ResponseEntity.ok(
    //                PaginatedApiResponse.<T>builder()
    //                        .success(true)
    //                        .message("Data retrieved successfully")
    //                        .data(PageResponse.<T>builder()
    //                                .content(content)
    //                                .page(pageable.getPageNumber())
    //                                .size(pageable.getPageSize())
    //                                .totalElements(totalElements)
    //                                .totalPages((int) Math.ceil((double) totalElements / pageable.getPageSize()))
    //                                .build())
    //                        .status(HttpStatus.OK.value())
    //                        .build()
    //        );
    //    }
    }
