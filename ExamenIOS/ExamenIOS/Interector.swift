
//
//  Intrtr.swift
//  ExamenIOS
//
//  Created by Marco Antonio Martínez Gutiérrez on 25/10/18.
//  Copyright © 2018 Marco Antonio Martínez Gutiérrez. All rights reserved.
//

import Foundation
import Alamofire
import CoreData

class Interector: PresentorToInterectorProtocol{
    
    let networker = NetworkManager()
    
    var presenter: InterectorToPresenterProtocol?
    
    func fetchNews() {
        
        networker.getArticles( ){  result, error in
            ///print(result)
            self.presenter!.newsFetched(news: result!)
        }
    }
    
    func fetchNew(id: Int) {
        
    }
    
    
}
