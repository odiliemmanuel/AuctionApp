package org.auctionsystem.AuctionSystem.controllers;

import org.auctionsystem.AuctionSystem.dtos.requests.CancelAuctionRequest;
import org.auctionsystem.AuctionSystem.dtos.requests.CreateAuctionRequest;
import org.auctionsystem.AuctionSystem.exceptions.AuctionDoesNotExistException;
import org.auctionsystem.AuctionSystem.exceptions.ProductAlreadyAuctionedBeforeBySellerException;
import org.auctionsystem.AuctionSystem.services.AuctionManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/seller")
public class AuctionManagementController {

    @Autowired
    AuctionManagementService auctionManagementService;


    @PostMapping("/create/new/auction")
    public ResponseEntity<?> createNewAuction(@RequestBody CreateAuctionRequest createAuctionRequest){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(auctionManagementService.organizeNewAuction(createAuctionRequest));
        }
        catch(ProductAlreadyAuctionedBeforeBySellerException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }


    @PostMapping("/cancel/auction")
    public ResponseEntity<?> cancelAnAuction(@RequestBody CancelAuctionRequest cancelAuctionRequest){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(auctionManagementService.cancelAuction(cancelAuctionRequest));
        }
        catch(AuctionDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }
}
