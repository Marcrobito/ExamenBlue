//
//  Presenter.swift
//  ExamenIOS
//
//  Created by Marco Antonio Martínez Gutiérrez on 25/10/18.
//  Copyright © 2018 Marco Antonio Martínez Gutiérrez. All rights reserved.
//

import Foundation

class Presenter: ViewToPresenterProtocol {
    
    var view: PresenterToViewProtocol?
    
    var interector: PresentorToInterectorProtocol?
    
    var router: PresenterToRouterProtocol?
    
    func updateView() {
        interector?.fetchNews()
    }
    
    
}

extension Presenter : InterectorToPresenterProtocol{
    func newsFetched(news: [HackerContent]) {
        view!.showNews(news: news)
    }
    
    
}
