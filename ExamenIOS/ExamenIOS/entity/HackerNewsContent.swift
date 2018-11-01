//
//  HackerNewsContent.swift
//  ExamenIOS
//
//  Created by Marco Antonio Martínez Gutiérrez on 31/10/18.
//  Copyright © 2018 Marco Antonio Martínez Gutiérrez. All rights reserved.
//

import Foundation

struct HackerContent:Codable {
    
    let by:String
    let descendants:Int?
    let id:Int
    let kids:[Int]
    let score:Int?
    let time:Int
    let title:String
    let type:String
    let url:String
    
    let parent:String?
    let text:String?
    let about:String?
    
    
    private enum CodingKeys: String, CodingKey {
        case by
        case descendants
        case id
        case kids
        case score
        case time
        case title
        case type
        case url
        case parent
        case text
        case about
    }
}
