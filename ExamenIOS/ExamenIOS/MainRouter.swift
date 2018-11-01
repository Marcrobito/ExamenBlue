//
//  Router.swift
//  ExamenIOS
//
//  Created by Marco Antonio Martínez Gutiérrez on 25/10/18.
//  Copyright © 2018 Marco Antonio Martínez Gutiérrez. All rights reserved.
//

import Foundation
import UIKit

class MainRouter: PresenterToRouterProtocol{
    
    class func createModule() ->UIViewController{
        let view = mainstoryboard.instantiateViewController(withIdentifier: "ViewController") as? ViewController
        
        var presenter: ViewToPresenterProtocol & InterectorToPresenterProtocol = Presenter()
        let interactor: PresentorToInterectorProtocol =  Interector()
        let router: PresenterToRouterProtocol = MainRouter()
        
        
        view!.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter
        
        return view!
        
    }
    
    static var mainstoryboard: UIStoryboard{
        return UIStoryboard(name:"Main",bundle: Bundle.main)
    }
}

