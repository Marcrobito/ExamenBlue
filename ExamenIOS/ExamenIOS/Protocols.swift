//
//  Contract.swift
//  ExamenIOS
//
//  Created by Marco Antonio Martínez Gutiérrez on 25/10/18.
//  Copyright © 2018 Marco Antonio Martínez Gutiérrez. All rights reserved.
//

import Foundation
import UIKit

protocol PresenterToViewProtocol: class {
    func showNews(news: [HackerContent])
    func showError()
}

protocol InterectorToPresenterProtocol: class {
    func newsFetched(news:[HackerContent])
    
}

protocol PresentorToInterectorProtocol: class {
    var presenter: InterectorToPresenterProtocol? {get set}
    func fetchNews()
    func fetchNew(id:Int)
    //func fetchLiveNews();
}

protocol ViewToPresenterProtocol: class {
    var view: PresenterToViewProtocol? {get set};
    var interector: PresentorToInterectorProtocol? {get set};
    var router: PresenterToRouterProtocol? {get set}
    func updateView();
}

protocol PresenterToRouterProtocol: class {
    static func createModule() -> UIViewController;
}
