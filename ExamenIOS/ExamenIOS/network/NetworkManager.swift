//
//  NetworkManager.swift
//  hoteles mision
//
//  Created by Marco Martínez on 04/06/18.
//  Copyright © 2018 Marco Martínez. All rights reserved.
//

import Foundation
import SwiftyJSON

struct NetworkManager {
    static let environment : NetworkEnvironment = .staging
    
    let router = Router<HackerNewsApi>()
    
    func getArticles(completion: @escaping (_ news: [HackerContent]?,_ error: String?)->()){
        router.request(.topstories) { data, response, error in
            //(index, item) in galleryArray.enumerated
            var hackerNews:[HackerContent] = []
            let group = DispatchGroup()
            (data!["data"]! as! [Int]).forEach { hackerNew in
                
                group.enter()
                
                self.getArticle(id: hackerNew){ result, error in
                    print(result)
                    
                    if result != nil{
                        hackerNews.append(result!)
                    }
                    group.leave()
                }
                
            }
            group.notify(queue: .main) {
                print(hackerNews)
                completion(hackerNews, nil)
                // do something here when loop finished
            }
            //print(news)
            
            /*for new in (data!["data"]! as! [Int]){
             
            }*/
            //completion(data!["data"]! as! [Int], nil)
        }
        
    }
    
    func getArticle(id:Int, completion: @escaping (_ new:HackerContent?,_ error: String?)->()){
        router.request(.story(id: id)) { data, response, error in
            
            print(data)
            let jsonDecoder = JSONDecoder()
            if data != nil{
                do{
                    let jsonData = try JSONSerialization.data(withJSONObject: data!)
                    let new = try jsonDecoder.decode(HackerContent.self, from: jsonData)
                    
                    completion(new,  error as! String?)
                }catch {
                    print("Error deserializing JSON: \(error)")
                    completion(nil, "Error deserializing JSON \(error)")
                }
            }
            //completion(data! as! HackerContent, nil)
            
        }
        
    }
    
    /*func getDestinations(completion: @escaping (_ destinations: DestinationList?,_ error: String?)->()){
        
        router.request(.destination) { data, response, error in
            let jsonDecoder = JSONDecoder()
            if data != nil{
                do{
                    let jsonData = try JSONSerialization.data(withJSONObject: data!)
                    let destinations = try jsonDecoder.decode( DestinationList.self, from: jsonData)
                    completion(destinations,  error as! String?)
                }catch {
                    print("Error deserializing JSON: \(error)")
                    completion(nil, "Error deserializing JSON \(error)")
                }
            }
        }
    }*/

    
}
