package com.example.sudokuwebapp.api.controller;

import com.example.sudokuwebapp.api.model.Board;
import com.example.sudokuwebapp.api.model.Number;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

    private final Board board;

    public BoardController(Board board) {
        this.board = board;
    }

    @GetMapping("/getBoard")
    public Number[][] fetchBoard(){
        return board.getBoard();
    }


    @GetMapping("/new")
    public Number[][] newBoard() {
        return board.newBoard();
    }

    @GetMapping("/reloadBoard")
    public Number[][] reloadBoard() {
        return board.reloadBoard();
    }

    @GetMapping("/number/{row}/{col}")
    public Number getNumber(@PathVariable int row, @PathVariable int col) {
        return board.getNumber(row, col);
    }

    @PostMapping("/number/{row}/{col}")
    public boolean setNumber(@PathVariable int row, @PathVariable int col, @RequestParam int number) {
        return board.setNumber(row, col, number);
    }
}
