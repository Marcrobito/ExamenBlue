//
//  Router.swift
//  hoteles mision
//
//  Created by Marco Martínez on 04/06/18.
//  Copyright © 2018 Marco Martínez. All rights reserved.
//

import Foundation
import Alamofire

public typealias NetworkRouterCompletion = (_ data: [String : Any]?,_ response: URLResponse?,_ error: Error?)->()

protocol NetworkRouter: class {
    associatedtype EndPoint: EndPointType
    func request(_ route: EndPoint, completion: @escaping NetworkRouterCompletion)
        func cancel()
}

class Router<EndPoint: EndPointType>: NetworkRouter {

    
    internal func cancel() {
        
    }
    
    /*let configuration = URLSessionConfiguration.default
    configuration.timeoutIntervalForRequest = 10 // seconds
    configuration.timeoutIntervalForResource = 10
    let alamoFireManager = Alamofire.SessionManager(configuration: configuration)*/

    
    func request(_ route: EndPoint, completion: @escaping NetworkRouterCompletion) {
        Alamofire.request(route.baseURL.appending(route.path), method: route.method, parameters: route.params, encoding: JSONEncoding.default, headers: route.headers).validate().responseJSON{ response in
            
            if let result = response.result.value as? [String : Any]{
                
                completion(result, response.response, response.error)
            }else if let result = response.result.value as? [Int]{
                
                completion(["data":result], response.response, response.error)
            }else{
                completion(nil, response.response, response.error)
            }
            
        }
        
    
    }
    
    
    
    

}
