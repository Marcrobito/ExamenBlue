//
//  HotelesEndPoint.swift
//  hoteles mision
//
//  Created by Marco Martínez on 04/06/18.
//  Copyright © 2018 Marco Martínez. All rights reserved.
//

import Foundation
import Alamofire

enum NetworkEnvironment {
    case qa
    case production
    case staging
}

public enum HackerNewsApi{
    case topstories
    case story(id:Int)
}

extension HackerNewsApi:EndPointType {
    ///
    var environmentBaseURL : String {
        switch NetworkManager.environment {
        case .production: return "https://hacker-news.firebaseio.com/v0"
        case .qa:  return "https://hacker-news.firebaseio.com/v0"
        case .staging: return "https://hacker-news.firebaseio.com/v0"
        }
    }
    
    var baseURL: String {
        guard URL(string: environmentBaseURL) != nil else { fatalError("baseURL could not be configured.")}
        return environmentBaseURL
    }
    
    var path: String {
        switch self {
        case .topstories: return  "/topstories.json?print=pretty"
        case .story(let id): return  "/item/\(id).json?print=pretty"
        }
    }
    
    var method: HTTPMethod {
        return .get
    }
    
    var fileDestination: String? {
        return nil
    }
    
    var headers: HTTPHeaders? {
        return nil
    }
    
    var params: [String : Any]? {
        return nil
    }
    

    
}
