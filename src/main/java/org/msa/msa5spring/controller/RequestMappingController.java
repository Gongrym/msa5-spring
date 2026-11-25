package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api") // 이렇게 클래스에 어노테이션
// 달아주면, 클래스 내부의 메소드 레벨에 "(/api)"
// 를 알아서 달아준다.
// 그래서 아래에 어노테션 4개 옆에 /api를 다 지워도됨
public class RequestMappingController {
    // @GetMapping("/api") << 이거 지움
    @GetMapping
    public String index(){
        return "유저 조회";
    }

    @PostMapping
    public String store(){ return "유저 생성"; }

    @PutMapping
    public String update(){
        return "유저 수정";
    }

    @DeleteMapping
    public String destroy(){
        return "유저 삭제";
    }

    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO){
        return String.format("%d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }

    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO){
        return String.format("segment = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }

    // 세그먼트 파라미터를 이용한 Path와 겹치지 않도록 주의
    // @GetMapping("/segment/1/my")
    // public String test(){
    //     return "Test";
    // }

    @PostMapping("/form")
    public String form(@ModelAttribute MultipartFile file){
        return String.format("%s : %s", file.getContentType(), file.getOriginalFilename());
    }

    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO){
        return String.format("json = %d : %s", queryRequestDTO.id(), queryRequestDTO.name());
    }
}

// Query Parameter 방식
// - 어노테이션 X, DTO객체 생성

// Segment Parameter 방식
// - @PathVariables, 각 키에 맞는 파라미터를 별도로 컨트롤러 파라미터로 생성
// - @ModelAttribute, DTO객체 생성

// Form Data 방식
// - @ModelAttribute, DTO객체 또는 컨트롤러 파라미터

// JSON 방식
// - @RequestBody, DTO객체